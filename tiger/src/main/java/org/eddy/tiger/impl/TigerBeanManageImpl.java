/**
 * 
 * @creatTime 下午1:34:09
 * @author Eddy
 */
package org.eddy.tiger.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.AnnotatedParameter;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Scope;
import javax.inject.Singleton;

import org.eddy.tiger.TigerBean;
import org.eddy.tiger.TigerBeanImpl;
import org.eddy.tiger.TigerBeanManage;
import org.eddy.tiger.annotated.AbstractAnnotatedCallable;
import org.eddy.tiger.annotated.Request;
import org.eddy.tiger.context.AbstractContext;
import org.eddy.tiger.context.TigerCreationalContext;
import org.eddy.tiger.context.impl.RequestContext;
import org.eddy.tiger.context.impl.SingletonContext;
import org.eddy.tiger.point.AbstractInjectionPoint;
import org.eddy.tiger.point.ConstructorInjectionPoint;
import org.eddy.tiger.point.FieldInjectionPoint;
import org.eddy.tiger.point.MethodInjectionPoint;

/**
 * @author Eddy
 * 
 */
@SuppressWarnings("all")
public class TigerBeanManageImpl extends TigerBeanManage {

	AbstractContext singleton = new SingletonContext();

	AbstractContext request = new RequestContext();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eddy.tiger.TigerBeanManage#createBean(java.lang.Class)
	 */
	@Override
	public <T> TigerBean<T> createBean(Class<T> beanClass) {
		Class<? extends Annotation> scop = getScop(beanClass);
		// 生成TigerBean
		TigerBean<T> bean = new TigerBeanImpl<>(beanClass, scop, this);
		if (scop.equals(Singleton.class)) {
			checkBeanName(bean, singleton);
			singleton.addBean(bean);
		} else if (scop.equals(Request.class)) {
			checkBeanName(bean, request);
			request.addBean(bean);
		}
		return bean;
	}

	private <T> void checkBeanName(TigerBean<T> bean, AbstractContext context) {
		TigerBean<?> b = context.getByName(bean.getName());
		if (null != b)
			throw new IllegalArgumentException("bean名字重复");
	}

	/**
	 * 获取scop属性
	 * 
	 * @param beanClass
	 * @return
	 * @creatTime 下午1:52:03
	 * @author Eddy
	 */
	private Class<? extends Annotation> getScop(Class<?> beanClass) {
		Annotation[] anns = beanClass.getAnnotations();
		for (Annotation ann : anns) {
			for (Annotation a : ann.annotationType().getAnnotations()) {
				if (a.annotationType().equals(Scope.class)) {
					return ann.annotationType();
				}
			}
		}
		return Singleton.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#getReference(javax.enterprise
	 * .inject.spi.Bean, java.lang.reflect.Type,
	 * javax.enterprise.context.spi.CreationalContext)
	 */
	@Override
	public Object getReference(Bean<?> bean, Type beanType, CreationalContext<?> ctx) {
		try {
			Class<? extends Annotation> scop = bean.getScope();
			AbstractContext con = (AbstractContext) getContext(scop);
			if (null == con)
				throw new IllegalArgumentException("未实现的scop");
			Object obj = con.get(bean);
			Set<InjectionPoint> points = bean.getInjectionPoints();
			for (InjectionPoint point : points) {
				if (!checkPoint(point))
					continue;
				// 更新注入点状态
				updatePointState(point);
				if (point instanceof FieldInjectionPoint) {
					Object param = getInjectableReference(point, con.getCreationalContext());
					Field f = (Field) point.getMember();
					f.set(obj, param);
					// 重新释放注入点, 以便后续使用
					((AbstractInjectionPoint) point).setState(AbstractInjectionPoint.OPEN);
				}
				if (point instanceof MethodInjectionPoint) {
					Method m = (Method) point.getMember();
					Object[] params = getInjectableReferenceForCallable(point, con.getCreationalContext());
					m.invoke(obj, params);
					// 重新释放注入点, 以便后续使用
					((AbstractInjectionPoint) point).setState(AbstractInjectionPoint.OPEN);
				}
			}
			return obj;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("反射调用权限验证失败", e);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("方法调用失败", e);
		}
	}

	/**
	 * 更新注入点状态
	 * 
	 * @param point
	 * @creatTime 下午3:56:39
	 * @author Eddy
	 */
	private void updatePointState(InjectionPoint point) {
		AbstractInjectionPoint p = (AbstractInjectionPoint) point;
		p.setState(AbstractInjectionPoint.PENDING);
	}

	/**
	 * 检查注入点状态方法
	 * 
	 * @param point
	 * @return true:可以执行注入, false: 不可执行注入
	 * @creatTime 下午1:55:16
	 * @author Eddy
	 */
	private boolean checkPoint(InjectionPoint point) {
		AbstractInjectionPoint p = (AbstractInjectionPoint) point;
		if (AbstractInjectionPoint.CLOSED == p.getState() || AbstractInjectionPoint.PENDING == p.getState()) {
			return false;
		} else if (AbstractInjectionPoint.OPEN == p.getState()) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#getInjectableReference(javax.
	 * enterprise.inject.spi.InjectionPoint,
	 * javax.enterprise.context.spi.CreationalContext)
	 */
	@Override
	public Object getInjectableReference(InjectionPoint ij, CreationalContext ctx) {
//		TigerBean<?> in = (TigerBean<?>) ((AbstractInjectionPoint) ij).getBean(ctx, ij.getType());
//		if (null == in) throw new IllegalArgumentException("未获取Bean实例 , 请检查注解配置");
		TigerBean<?> in = getBean(ij, ctx, ij.getType());
		return in == null ? null : getReference(in);
	}
	
	/* (non-Javadoc)
	 * @see org.eddy.tiger.TigerBeanManage#getBean(org.eddy.tiger.point.AbstractInjectionPoint, javax.enterprise.context.spi.CreationalContext, java.lang.reflect.Type)
	 */
	public TigerBean<?> getBean(InjectionPoint ij, CreationalContext ctx, Type type) {
		TigerBean<?> in = (TigerBean<?>) ((AbstractInjectionPoint) ij).getBean(ctx, type);
		if (null == in) throw new IllegalArgumentException("未获取Bean实例 , 请检查注解配置");
		return in;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eddy.tiger.TigerBeanManage#getReference(javax.enterprise.inject.spi
	 * .Bean)
	 */
	@Override
	public <T> T getReference(Bean<T> bean) {
		return (T) getReference(bean, null, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.BeanManager#getContext(java.lang.Class)
	 */
	@Override
	public Context getContext(Class<? extends Annotation> scopeType) {
		if (Singleton.class.equals(scopeType)) {
			return singleton;
		} else if (Request.class.equals(scopeType)) {
			return request;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eddy.tiger.TigerBeanManage#getInjectableReferenceForCallable(javax
	 * .enterprise.inject.spi.InjectionPoint,
	 * javax.enterprise.context.spi.CreationalContext)
	 */
	@Override
	public Object[] getInjectableReferenceForCallable(InjectionPoint ij, CreationalContext ctx) {
		AbstractAnnotatedCallable<?> callable = (AbstractAnnotatedCallable<?>) ij.getAnnotated();
		List<?> params = callable.getParameters();
		Object[] result = new Object[params.size()];
		TigerCreationalContext context = (TigerCreationalContext) ctx;
		for (Object o : params) {
			AnnotatedParameter ap = (AnnotatedParameter) o;
//			TigerBean<?> in = (TigerBean<?>) ((AbstractInjectionPoint) ij).getBean(ctx, ap.getBaseType());
//			if (null == in) throw new IllegalArgumentException("未获取Bean实例 , 请检查注解配置");
			TigerBean<?> in = getBean(ij, ctx, ap.getBaseType());
			result[ap.getPosition()] = in == null ? null : getReference(in);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.BeanManager#getBeans(java.lang.String)
	 */
	@Override
	public Set<Bean<?>> getBeans(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}

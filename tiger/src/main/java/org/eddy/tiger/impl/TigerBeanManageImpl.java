/**
 * 
 * @creatTime 下午1:34:09
 * @author Eddy
 */
package org.eddy.tiger.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;

import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Scope;
import javax.inject.Singleton;

import org.eddy.tiger.TigerBean;
import org.eddy.tiger.TigerBeanImpl;
import org.eddy.tiger.TigerBeanManage;
import org.eddy.tiger.context.AbstractContext;
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
	
	private static TigerBeanManage manage = new TigerBeanManageImpl();
	
	public static TigerBeanManage getInstance() {
		return manage;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eddy.tiger.TigerBeanManage#createBean(java.lang.Class)
	 */
	@Override
	public <T> TigerBean<T> createBean(Class<T> beanClass) {
		Class<? extends Annotation> scop = getScop(beanClass);
		//生成TigerBean
		TigerBean<T> bean = new TigerBeanImpl<>(beanClass, scop, this);
		
		if (scop.equals(Singleton.class)) {
			singleton.addBean(bean);
		}
		return bean;
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

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.BeanManager#getReference(javax.enterprise.inject.spi.Bean, java.lang.reflect.Type, javax.enterprise.context.spi.CreationalContext)
	 */
	@Override
	public Object getReference(Bean<?> bean, Type beanType, CreationalContext<?> ctx) {
		try {
			Class<? extends Annotation> scop = bean.getScope();
			if (Singleton.class.equals(scop)) {
				Object obj= singleton.get(bean);
				Set<InjectionPoint> points = bean.getInjectionPoints();
				for (InjectionPoint point : points) {
					if (point instanceof ConstructorInjectionPoint) continue;
					Object i = getInjectableReference(point, singleton.getCreationalContext());
					if (point instanceof FieldInjectionPoint) {
						Field f = (Field) point.getMember();
						f.set(obj, i);
					}
					if (point instanceof MethodInjectionPoint) {
						Method m = (Method) point.getMember();
						m.invoke(obj, i);
					}
				}
				return obj;
			} else {
				return null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.BeanManager#getInjectableReference(javax.enterprise.inject.spi.InjectionPoint, javax.enterprise.context.spi.CreationalContext)
	 */
	@Override
	public Object getInjectableReference(InjectionPoint ij, CreationalContext ctx) {
		TigerBean<?> in = (TigerBean<?>) ((AbstractInjectionPoint) ij).getBean(ctx, ij.getType());
		return in.create(ctx);
	}


	/* (non-Javadoc)
	 * @see org.eddy.tiger.TigerBeanManage#getReference(javax.enterprise.inject.spi.Bean)
	 */
	@Override
	public Object getReference(Bean<?> bean) {
		return getReference(bean, null, null);
	}


	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.BeanManager#getContext(java.lang.Class)
	 */
	@Override
	public Context getContext(Class<? extends Annotation> scopeType) {
		if (Singleton.class.equals(scopeType)) {
			return singleton;
		}
		return null;
	}

}

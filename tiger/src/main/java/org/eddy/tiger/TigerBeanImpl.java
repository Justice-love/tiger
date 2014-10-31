/**
 * 
 * @creatTime 上午8:53:16
 * @author Eddy
 */
package org.eddy.tiger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.AnnotatedConstructor;
import javax.enterprise.inject.spi.AnnotatedField;
import javax.enterprise.inject.spi.AnnotatedMethod;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;
import javax.inject.Qualifier;

import org.apache.commons.lang3.StringUtils;
import org.eddy.tiger.annotated.impl.AnnotatedTypeImpl;
import org.eddy.tiger.context.AbstractContext;
import org.eddy.tiger.context.TigerCreationalContext;
import org.eddy.tiger.point.ConstructorInjectionPoint;
import org.eddy.tiger.point.FieldInjectionPoint;
import org.eddy.tiger.point.MethodInjectionPoint;

/**
 * @author Eddy
 * 
 */
@SuppressWarnings("all")
public class TigerBeanImpl<T> implements TigerBean<T> {

	private Class<?> beanClass;
	private String name;
	private Class<? extends Annotation> scop;
	private Set<Annotation> qualifiers;
	private Set<Type> types;
	private Set<FieldInjectionPoint> fieldInjectionPoints;
	private Set<ConstructorInjectionPoint> constructorInjectionPoints;
	private Set<MethodInjectionPoint> methodInjectionPoints;
	private AnnotatedType<T> type;
	private TigerBeanManage manage;
	
	public TigerBeanImpl(Class<T> beanClass, Class<? extends Annotation> scop, TigerBeanManage manage) {
		this.beanClass = beanClass;
		this.name = createName();
		this.scop = scop;
		this.qualifiers = createQualifiers();
		this.types = createTypes();
		this.manage = manage;
		this.type = new AnnotatedTypeImpl<T>(beanClass);
		
		this.fieldInjectionPoints = createFieldInjectionPoints();
		this.constructorInjectionPoints = createConstructorInjectionPoints();
		this.methodInjectionPoints = createMethodInjectionPoints();
	}
 
	/**
	 * 初始化方法注入点
	 * @return
	 * @creatTime 上午8:52:05
	 * @author Eddy
	 */
	private Set<MethodInjectionPoint> createMethodInjectionPoints() {
		Set<MethodInjectionPoint> result = new HashSet<>();
		Set<AnnotatedMethod<? super T>> set = type.getMethods();
		for (AnnotatedMethod<? super T> method : set) {
			result.add(new MethodInjectionPoint(method));
		}
		return result;
	}

	/**
	 * 初始化构造函数注入点
	 * @return
	 * @creatTime 上午8:51:43
	 * @author Eddy
	 */
	private Set<ConstructorInjectionPoint> createConstructorInjectionPoints() {
		Set<ConstructorInjectionPoint> result = new HashSet<>();
		Set<AnnotatedConstructor<T>> set = type.getConstructors();
		for (AnnotatedConstructor<T> constructor : set) {
			result.add(new ConstructorInjectionPoint(constructor));
		}
		if (result.size() > 1) {
			throw new IllegalArgumentException("可注入的构造函数数目大于1");
		}
		return result;
	}

	/**
	 * 初始化field注入点
	 * @return
	 * @creatTime 上午8:51:11
	 * @author Eddy
	 */
	private Set<FieldInjectionPoint> createFieldInjectionPoints() {
		Set<FieldInjectionPoint> result = new HashSet<>();
		Set<AnnotatedField<? super T>> set = type.getFields();
		for (AnnotatedField<? super T> field : set) {
			result.add(new FieldInjectionPoint(field));
		}
		return result;
	}

	/**
	 * 生成types
	 * @return
	 * @creatTime 上午9:43:36
	 * @author Eddy
	 */
	private Set<Type> createTypes() {
		Set<Type> result = new HashSet<>();
		result.add(beanClass);
		
		Type supperClass = beanClass;
		while(supperClass != null && supperClass != Object.class) {
			Type[] interfaces = null;
			if (supperClass instanceof Class) {
				supperClass = ((Class<?>)supperClass).getGenericSuperclass();
				interfaces = ((Class<?>)supperClass).getGenericInterfaces();
			} else if (supperClass instanceof ParameterizedType) {
				Type rawType = ((ParameterizedType)supperClass).getRawType();
				supperClass = ((Class<?>)rawType).getGenericSuperclass();
				interfaces = ((Class<?>)rawType).getGenericInterfaces();
			}
			if (supperClass != Object.class) {
				result.add(supperClass);
			}
			if (interfaces != null) {
				for (Type t : interfaces) {
					result.add(t);
					result.addAll(getInterfaces((Class<? super T>)t));
				}
			}
		}
		return result;
	}

	/**
	 * 获取接口
	 * @param i2
	 * @return
	 * @creatTime 上午10:03:47
	 * @author Eddy
	 */
    private <T> Set<Type> getInterfaces(Class<T> interfaceClass) {
        final Set<Type> ret = new HashSet<Type>();
        final Class<?>[] interfaces = interfaceClass.getInterfaces();
        if (interfaces.length == 0) {
            return ret;
        } else {
            for (final Class<?> i : interfaces) {
                ret.add(i);
                ret.addAll(getInterfaces(i));
            }

            return ret;
        }
    }

	/**
	 * 生成bean name;
	 * @return
	 * @creatTime 上午9:17:33
	 * @author Eddy
	 */
	private String createName() {
		if (beanClass.isAnnotationPresent(Named.class)) {
			Named name = beanClass.getAnnotation(Named.class);
			return name.value();
		} else {
			return beanClass.getSimpleName().substring(0, 1).toLowerCase() + beanClass.getSimpleName().substring(1, beanClass.getSimpleName().length());
		}
	}

	/**
	 * 生成Qualifiers列表
	 * @return
	 * @creatTime 上午9:12:14
	 * @author Eddy
	 */
	private Set<Annotation> createQualifiers() {
		Set<Annotation> result = new HashSet<>();
		for (Annotation ann : beanClass.getAnnotations()) {
			for (Annotation as : ann.annotationType().getAnnotations()) {
				if (as.annotationType().equals(Qualifier.class)) {
					result.add(ann);
				}
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.Bean#getBeanClass()
	 */
	@Override
	public Class<?> getBeanClass() {
		return this.beanClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.Bean#getInjectionPoints()
	 */
	@Override
	public Set<InjectionPoint> getInjectionPoints() {
		Set<InjectionPoint> result = new HashSet<>();
		result.addAll(this.constructorInjectionPoints);
		result.addAll(this.fieldInjectionPoints);
		result.addAll(this.methodInjectionPoints);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.Bean#isNullable()
	 */
	@Override
	public boolean isNullable() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.context.spi.Contextual#create(javax.enterprise.context
	 * .spi.CreationalContext)
	 */
	@Override
	public T create(CreationalContext creationalContext) {
		TigerBean bean = (TigerBean) ((TigerCreationalContext) creationalContext).get(this.getName());
		try {
			if (bean.isConstructor()) {
				ConstructorInjectionPoint point = bean.getConstructorInjectionPoint();
				AbstractContext context = (AbstractContext) manage.getContext(bean.getScope());
//				Object obj = manage.getInjectableReference(point, context.getCreationalContext());
				Object[] params = manage.getInjectableReferenceForCallable(point, context.getCreationalContext());
				return (T) ((Constructor<?>) point.getMember()).newInstance(params);
			} else {
				return (T) bean.getBeanClass().newInstance();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.context.spi.Contextual#destroy(java.lang.Object,
	 * javax.enterprise.context.spi.CreationalContext)
	 */
	@Override
	public void destroy(T instance, CreationalContext<T> creationalContext) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.BeanAttributes#getTypes()
	 */
	@Override
	public Set<Type> getTypes() {
		return this.types;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.BeanAttributes#getQualifiers()
	 */
	@Override
	public Set<Annotation> getQualifiers() {
		return this.qualifiers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.BeanAttributes#getScope()
	 */
	@Override
	public Class<? extends Annotation> getScope() {
		return this.scop;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.BeanAttributes#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.BeanAttributes#getStereotypes()
	 */
	@Override
	public Set<Class<? extends Annotation>> getStereotypes() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.BeanAttributes#isAlternative()
	 */
	@Override
	public boolean isAlternative() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eddy.tiger.TigerBean#isRight(java.lang.String, java.lang.reflect.Type)
	 */
	@Override
	public boolean isRight(String name, Type type) {
		if (StringUtils.isEmpty(name) && null == type) {
			throw new IllegalArgumentException("参数不能都为空");
		}
		if(StringUtils.isEmpty(name) ^ null == type) {
			if (StringUtils.isEmpty(name)) {
				return this.getTypes().contains(type);
			} else {
				return name.equals(this.getName());
			}
		} else {
			return name.equals(this.getName()) && this.getTypes().contains(type);
		}
	}

	/* (non-Javadoc)
	 * @see org.eddy.tiger.TigerBean#isConstructor()
	 */
	@Override
	public boolean isConstructor() {
		return this.constructorInjectionPoints.size() == 1;
	}

	/* (non-Javadoc)
	 * @see org.eddy.tiger.TigerBean#getConstructorInjectionPoint()
	 */
	@Override
	public ConstructorInjectionPoint getConstructorInjectionPoint() {
		return this.constructorInjectionPoints.iterator().next();
	}

}

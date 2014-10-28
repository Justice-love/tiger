/**
 * 
 * @creatTime 上午8:53:16
 * @author Eddy
 */
package org.eddy.tiger;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;
import javax.inject.Qualifier;

/**
 * @author Eddy
 * 
 */
public class TigerBeanImpl<T> implements TigerBean<T> {

	private Class<?> beanClass;
	private String name;
	private Class<? extends Annotation> scop;
	private Set<Annotation> qualifiers;
	private Set<Type> types;
	
	public TigerBeanImpl(Class<?> beanClass, Class<? extends Annotation> scop) {
		this.beanClass = beanClass;
		this.name = createName();
		this.scop = scop;
		this.qualifiers = createQualifiers();
		this.types = createTypes();
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
		// TODO Auto-generated method stub
		return null;
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
	public T create(CreationalContext<T> creationalContext) {
		// TODO Auto-generated method stub
		return null;
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

}

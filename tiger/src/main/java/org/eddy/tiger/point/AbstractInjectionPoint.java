/**
 * 
 * @creatTime 下午3:44:07
 * @author Eddy
 */
package org.eddy.tiger.point;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Qualifier;

/**
 * @author Eddy
 *
 */
public abstract class AbstractInjectionPoint implements InjectionPoint{

	protected Annotated annotated;
	
	public AbstractInjectionPoint(Annotated annotated) {
		this.annotated = annotated;
	}
	
	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.InjectionPoint#getType()
	 */
	@Override
	public Type getType() {
		return annotated.getBaseType();
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.InjectionPoint#getQualifiers()
	 */
	@Override
	public Set<Annotation> getQualifiers() {
		Set<Annotation> set = this.annotated.getAnnotations();
		Set<Annotation> result = new HashSet<>();
		for (Annotation ann : set) {
			Annotation[] as = ann.annotationType().getAnnotations();
			for (Annotation a : as) {
				if (a.annotationType().equals(Qualifier.class)) {
					result.add(ann);
				}
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.InjectionPoint#getBean()
	 */
	@Override
	public Bean<?> getBean() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.InjectionPoint#getAnnotated()
	 */
	@Override
	public Annotated getAnnotated() {
		return this.annotated;
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.InjectionPoint#isDelegate()
	 */
	@Override
	public boolean isDelegate() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.InjectionPoint#isTransient()
	 */
	@Override
	public boolean isTransient() {
		// TODO Auto-generated method stub
		return false;
	}

}

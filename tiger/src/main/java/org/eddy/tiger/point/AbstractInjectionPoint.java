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

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Qualifier;

import org.eddy.tiger.context.TigerCreationalContext;

/**
 * @author Eddy
 *
 */
public abstract class AbstractInjectionPoint implements InjectionPoint{

	protected Annotated annotated;
	
	/**
	 * 开启状态
	 */
	public static final int OPEN = 0;
	
	/**
	 * 等待注入状态
	 */
	public static final int PENDING = 1;
	
	/**
	 * 关闭状态
	 */
	public static final int CLOSED = 2;
	
	/**
	 * 注入点状态
	 */
	private int state = OPEN;
	
	public AbstractInjectionPoint(Annotated annotated) {
		this.annotated = annotated;
	}
	
	public AbstractInjectionPoint(Annotated annotated, int state) {
		this.annotated = annotated;
		this.state = state;
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
	
	public Bean<?> getBean(CreationalContext<?> ctx, Type type) {
		return (Bean<?>) ((TigerCreationalContext<?>) ctx).get(null, type);
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}

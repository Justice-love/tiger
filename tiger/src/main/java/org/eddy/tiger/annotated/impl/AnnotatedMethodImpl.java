/**
 * 
 * @creatTime 下午12:54:09
 * @author Eddy
 */
package org.eddy.tiger.annotated.impl;

import java.lang.reflect.Method;

import javax.enterprise.inject.spi.AnnotatedMethod;

/**
 * @author Eddy
 * @param <X>
 * 
 */
public class AnnotatedMethodImpl<X> extends AnnotatedCallableImpl<X> implements AnnotatedMethod<X> {
	
	private Method method;
	
	/**
	 * 构造函数
	 * 
	 * @creatTime 下午12:56:38
	 * @author Eddy
	 */
	public AnnotatedMethodImpl(Method method) {
		super(method);
		this.method = method;
		if (!method.isAccessible()) method.setAccessible(true);
	}
	
	/**
	 * 构造函数
	 * 
	 * @creatTime 下午12:56:38
	 * @author Eddy
	 */
	public AnnotatedMethodImpl() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.AnnotatedMethod#getJavaMember()
	 */
	@Override
	public Method getJavaMember() {
		return method;
	}

}

/**
 * 
 * @creatTime 下午4:53:17
 * @author Eddy
 */
package org.eddy.tiger.annotated.impl;

import java.lang.reflect.Constructor;

import javax.enterprise.inject.spi.AnnotatedConstructor;

/**
 * @author Eddy
 * 
 */
public class AnnotatedConstructorImpl<X> extends AnnotatedCallableImpl<X> implements AnnotatedConstructor<X> {

	private Constructor<X> constructor;

	/**
	 * 构造函数
	 * 
	 * @creatTime 下午12:56:25
	 * @author Eddy
	 */
	public AnnotatedConstructorImpl() {
		// TODO Auto-generated constructor stub
	}

	public AnnotatedConstructorImpl(Constructor constructor) {
		super(constructor);
		this.constructor = constructor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.AnnotatedConstructor#getJavaMember()
	 */
	@Override
	public Constructor<X> getJavaMember() {
		return this.constructor;
	}

}

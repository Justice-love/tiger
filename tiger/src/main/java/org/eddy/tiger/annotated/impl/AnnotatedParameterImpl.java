/**
 * 
 * @creatTime 下午4:55:28
 * @author Eddy
 */
package org.eddy.tiger.annotated.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

import javax.enterprise.inject.spi.AnnotatedCallable;
import javax.enterprise.inject.spi.AnnotatedParameter;

import org.apache.commons.lang3.StringUtils;
import org.eddy.tiger.annotated.AnnotatedName;

/**
 * @author Eddy
 * 
 */
@SuppressWarnings("all")
public class AnnotatedParameterImpl<X> implements AnnotatedParameter<X>, AnnotatedName{
	private AnnotatedCallable<X> callable;
	private int index;
	private Type type;
	private Set<Annotation> annotations;
	private String name;
	/**
	 * 构造函数
	 * 
	 * @creatTime 下午4:59:09
	 * @author Eddy
	 */
	public AnnotatedParameterImpl() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 构造函数
	 * 
	 * @creatTime 下午4:59:09
	 * @author Eddy
	 */
	public AnnotatedParameterImpl(AnnotatedCallable<X> callable, int index, Type type, Set<Annotation> annotations) {
		this.callable = callable;
		this.index = index;
		this.type = type;
		this.annotations = annotations;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.Annotated#getBaseType()
	 */
	@Override
	public Type getBaseType() {
		return this.type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.Annotated#getTypeClosure()
	 */
	@Override
	public Set<Type> getTypeClosure() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.Annotated#getAnnotation(java.lang.Class)
	 */
	@Override
	public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
		for (Annotation ann : annotations) {
			if (ann.annotationType().equals(annotationType)) {
				return (T) ann;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.Annotated#getAnnotations()
	 */
	@Override
	public Set<Annotation> getAnnotations() {
		return this.annotations;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.Annotated#isAnnotationPresent(java.lang.Class
	 * )
	 */
	@Override
	public boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {
		for (Annotation ann : annotations) {
			if (ann.annotationType().equals(annotationType)) return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.AnnotatedParameter#getPosition()
	 */
	@Override
	public int getPosition() {
		return this.index;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.AnnotatedParameter#getDeclaringCallable()
	 */
	@Override
	public AnnotatedCallable<X> getDeclaringCallable() {
		return this.callable;
	}

	/* (non-Javadoc)
	 * @see org.eddy.tiger.annotated.AnnotatedName#getAnnotatedName()
	 */
	@Override
	public String getAnnotatedName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see org.eddy.tiger.annotated.AnnotatedName#setAnnotatedName(java.lang.String)
	 */
	@Override
	public void setAnnotatedName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see org.eddy.tiger.annotated.AnnotatedName#isNamed()
	 */
	@Override
	public boolean isNamed() {
		return !StringUtils.isEmpty(this.name);
	}
	
}

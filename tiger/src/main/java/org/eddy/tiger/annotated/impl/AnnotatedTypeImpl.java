/**
 * 
 * @creatTime 下午3:37:33
 * @author Eddy
 */
package org.eddy.tiger.annotated.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.inject.spi.AnnotatedConstructor;
import javax.enterprise.inject.spi.AnnotatedField;
import javax.enterprise.inject.spi.AnnotatedMethod;
import javax.enterprise.inject.spi.AnnotatedType;

/**
 * @author Eddy
 *
 */
public class AnnotatedTypeImpl<X> implements AnnotatedType<X> {

	private Class<X> beanClass;
	private Set<AnnotatedField<X>> annotatedFields = new HashSet<>();
	private Set<AnnotatedConstructor<X>> annotatedConstructors = new HashSet<>();
	private Set<AnnotatedMethod<X>> annotatedMethods = new HashSet<>();
	/**
	 * 构造函数
	 * @creatTime 下午3:44:05
	 * @author Eddy
	 */
	public AnnotatedTypeImpl() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 构造函数
	 * @creatTime 下午3:44:05
	 * @author Eddy
	 */
	public AnnotatedTypeImpl(Class<X> glass) {
		this.beanClass = glass;
		initFields();
		initConstructor();
		initMethods();
	}

	/**
	 * 
	 * @creatTime 下午2:05:20
	 * @author Eddy
	 */
	private void initMethods() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 * @creatTime 下午2:05:16
	 * @author Eddy
	 */
	private void initConstructor() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 * @creatTime 下午1:54:49
	 * @author Eddy
	 */
	private void initFields() {
		
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.Annotated#getBaseType()
	 */
	@Override
	public Type getBaseType() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.Annotated#getTypeClosure()
	 */
	@Override
	public Set<Type> getTypeClosure() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.Annotated#getAnnotation(java.lang.Class)
	 */
	@Override
	public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.Annotated#getAnnotations()
	 */
	@Override
	public Set<Annotation> getAnnotations() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.Annotated#isAnnotationPresent(java.lang.Class)
	 */
	@Override
	public boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.AnnotatedType#getJavaClass()
	 */
	@Override
	public Class<X> getJavaClass() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.AnnotatedType#getConstructors()
	 */
	@Override
	public Set<AnnotatedConstructor<X>> getConstructors() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.AnnotatedType#getMethods()
	 */
	@Override
	public Set<AnnotatedMethod<? super X>> getMethods() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.AnnotatedType#getFields()
	 */
	@Override
	public Set<AnnotatedField<? super X>> getFields() {
		// TODO Auto-generated method stub
		return null;
	}
	

}

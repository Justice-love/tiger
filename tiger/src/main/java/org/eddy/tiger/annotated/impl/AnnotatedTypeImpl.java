/**
 * 
 * @creatTime 下午3:37:33
 * @author Eddy
 */
package org.eddy.tiger.annotated.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.inject.spi.AnnotatedConstructor;
import javax.enterprise.inject.spi.AnnotatedField;
import javax.enterprise.inject.spi.AnnotatedMethod;
import javax.enterprise.inject.spi.AnnotatedType;

import org.eddy.tiger.util.Reflects;

/**
 * @author Eddy
 *
 */
public class AnnotatedTypeImpl<X> implements AnnotatedType<X> {

	private Class<X> beanClass;
	private Set<AnnotatedField<? super X>> annotatedFields = new HashSet<>();
	private Set<AnnotatedConstructor<X>> annotatedConstructors = new HashSet<>();
	private Set<AnnotatedMethod<? super X>> annotatedMethods = new HashSet<>();
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
		Set<Method> set = Reflects.getMethods(this.beanClass);
		for (Method method : set) {
			annotatedMethods.add(new AnnotatedMethodImpl<X>(method));
		}
	}

	/**
	 * 
	 * @creatTime 下午2:05:16
	 * @author Eddy
	 */
	private void initConstructor() {
		Set<Constructor<?>> set = Reflects.getConstructors(this.beanClass);
		for (Constructor<?> constructor : set) {
			annotatedConstructors.add(new AnnotatedConstructorImpl<X>(constructor));
		}
	}

	/**
	 * 
	 * @creatTime 下午1:54:49
	 * @author Eddy
	 */
	private void initFields() {
		Set<Field> set = Reflects.getFields(this.beanClass);
		for (Field field : set) {
			annotatedFields.add(new AnnotatedFieldImpl<X>(field));
		}
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.Annotated#getBaseType()
	 */
	@Override
	public Type getBaseType() {
		return this.beanClass;
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.Annotated#getTypeClosure()
	 */
	@Override
	public Set<Type> getTypeClosure() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.Annotated#getAnnotation(java.lang.Class)
	 */
	@Override
	public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
		return this.beanClass.getAnnotation(annotationType);
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.Annotated#getAnnotations()
	 */
	@Override
	public Set<Annotation> getAnnotations() {
		return new HashSet<>(Arrays.asList(this.beanClass.getAnnotations()));
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.Annotated#isAnnotationPresent(java.lang.Class)
	 */
	@Override
	public boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {
		return this.beanClass.isAnnotationPresent(annotationType);
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.AnnotatedType#getJavaClass()
	 */
	@Override
	public Class<X> getJavaClass() {
		return this.beanClass;
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.AnnotatedType#getConstructors()
	 */
	@Override
	public Set<AnnotatedConstructor<X>> getConstructors() {
		return this.annotatedConstructors;
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.AnnotatedType#getMethods()
	 */
	@Override
	public Set<AnnotatedMethod<? super X>> getMethods() {
		return this.annotatedMethods;
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.AnnotatedType#getFields()
	 */
	@Override
	public Set<AnnotatedField<? super X>> getFields() {
		return this.annotatedFields;
	}
	

}

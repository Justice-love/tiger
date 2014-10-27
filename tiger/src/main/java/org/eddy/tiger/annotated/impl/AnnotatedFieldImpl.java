/**
 * 
 * @creatTime 下午1:32:26
 * @author Eddy
 */
package org.eddy.tiger.annotated.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.inject.spi.AnnotatedField;
import javax.enterprise.inject.spi.AnnotatedType;

/**
 * @author Eddy
 *
 */
public class AnnotatedFieldImpl<X> implements AnnotatedField<X> {

	private Field field;
	
	/**
	 * 构造函数
	 * @creatTime 下午1:33:03
	 * @author Eddy
	 */
	public AnnotatedFieldImpl() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 构造函数
	 * @creatTime 下午1:33:03
	 * @author Eddy
	 */
	public AnnotatedFieldImpl(Field field) {
		this.field = field;
		if (!field.isAccessible()) field.setAccessible(true);
	}
	
	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.AnnotatedMember#isStatic()
	 */
	@Override
	public boolean isStatic() {
		return Modifier.isStatic(field.getModifiers());
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.AnnotatedMember#getDeclaringType()
	 */
	@Override
	public AnnotatedType<X> getDeclaringType() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.Annotated#getBaseType()
	 */
	@Override
	public Type getBaseType() {
		return field.getType();
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
		return this.field.getAnnotation(annotationType);
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.Annotated#getAnnotations()
	 */
	@Override
	public Set<Annotation> getAnnotations() {
		return new HashSet<>(Arrays.asList(this.field.getAnnotations()));
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.Annotated#isAnnotationPresent(java.lang.Class)
	 */
	@Override
	public boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {
		return this.field.isAnnotationPresent(annotationType);
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.AnnotatedField#getJavaMember()
	 */
	@Override
	public Field getJavaMember() {
		return this.field;
	}

}

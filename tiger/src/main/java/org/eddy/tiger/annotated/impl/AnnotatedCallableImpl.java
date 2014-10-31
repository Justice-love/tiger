/**
 * 
 * @creatTime 上午10:53:22
 * @author Eddy
 */
package org.eddy.tiger.annotated.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.inject.spi.AnnotatedCallable;
import javax.enterprise.inject.spi.AnnotatedParameter;
import javax.enterprise.inject.spi.AnnotatedType;

import org.eddy.tiger.annotated.AbstractAnnotatedCallable;

/**
 * @author Eddy
 * 
 */
@SuppressWarnings("all")
public abstract class AnnotatedCallableImpl<X> extends AbstractAnnotatedCallable<X> {
	private Member member;
	private List<AnnotatedParameter<X>> parameters;

	/**
	 * 构造函数
	 * 
	 * @creatTime 上午10:57:54
	 * @author Eddy
	 */
	public AnnotatedCallableImpl() {
		// TODO Auto-generated constructor stub
	}

	public AnnotatedCallableImpl(Member member) {
		this.member = member;
		parameters = new ArrayList<AnnotatedParameter<X>>();
		Type[] parameterType = null;
		Annotation[][] parameterAnnotations = null;
		if (member instanceof Method) {
			parameterType = ((Method) member).getGenericParameterTypes();
			parameterAnnotations = ((Method) member).getParameterAnnotations();
		} else if (member instanceof Constructor) {
			parameterType = ((Constructor) member).getGenericParameterTypes();
			parameterAnnotations = ((Constructor) member).getParameterAnnotations();
		}

		for (int i = 0; i < parameterType.length; i++) {
			final Type parameter = parameterType[i];

			// XXX: consider all annotations as the inject relevent annotation
			final Annotation[] annotations = parameterAnnotations[i];
			final Set<Annotation> annotationSet = new HashSet<Annotation>(Arrays.asList(annotations));
			AnnotatedParameter p = new AnnotatedParameterImpl<>(this, i, parameter, annotationSet);
			parameters.add(p);
		}
		//#1 去除方法或构造函数注入参数仅为1的限制
//		if (parameters.size() != 1) {
//			throw new IllegalArgumentException("注入方法或构造函数仅支持单个参数");
//		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.AnnotatedMember#isStatic()
	 */
	@Override
	public boolean isStatic() {
		return Modifier.isStatic(this.member.getModifiers());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.AnnotatedMember#getDeclaringType()
	 */
	@Override
	public AnnotatedType<X> getDeclaringType() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.Annotated#getBaseType()
	 */
	@Override
	public Type getBaseType() {
		return parameters.get(0).getBaseType();
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
		if (this.member instanceof Method) {
			return ((Method) member).getAnnotation(annotationType);
		} else if (this.member instanceof Constructor) {
			return (T) ((Constructor) member).getAnnotation(annotationType);
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
		if (this.member instanceof Method) {
			return new HashSet<>(Arrays.asList(((Method) member).getAnnotations()));
		} else if (this.member instanceof Constructor) {
			return new HashSet<>(Arrays.asList(((Constructor) member).getAnnotations()));
		}
		return null;
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
		if (this.member instanceof Method) {
			return ((Method) member).isAnnotationPresent(annotationType);
		} else if (this.member instanceof Constructor) {
			return ((Constructor) member).isAnnotationPresent(annotationType);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.AnnotatedCallable#getParameters()
	 */
	@Override
	public List<AnnotatedParameter<X>> getParameters() {
		return this.parameters;
	}
	
	/* (non-Javadoc)
	 * @see org.eddy.tiger.annotated.AbstractAnnotatedCallable#getBaseTypeForSet()
	 */
	@Override
	public Set<Type> getBaseTypeForSet() {
		Set<Type> result = new HashSet<>();
		for (AnnotatedParameter<X> param : parameters) {
			result.add(param.getBaseType());
		}
		return result;
	}

}

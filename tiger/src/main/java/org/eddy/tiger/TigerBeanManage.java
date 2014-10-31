/**
 * 
 * @creatTime 下午3:21:57
 * @author Eddy
 */
package org.eddy.tiger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

import javax.el.ELResolver;
import javax.el.ExpressionFactory;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.AnnotatedField;
import javax.enterprise.inject.spi.AnnotatedMember;
import javax.enterprise.inject.spi.AnnotatedMethod;
import javax.enterprise.inject.spi.AnnotatedParameter;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanAttributes;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Decorator;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.InjectionTarget;
import javax.enterprise.inject.spi.InjectionTargetFactory;
import javax.enterprise.inject.spi.InterceptionType;
import javax.enterprise.inject.spi.Interceptor;
import javax.enterprise.inject.spi.ObserverMethod;
import javax.enterprise.inject.spi.ProducerFactory;

/**
 * @author Eddy
 * 
 */
@SuppressWarnings("all")
public abstract class TigerBeanManage implements BeanManager {

	public abstract <T> TigerBean<T> createBean(Class<T> beanClass);
	
	public abstract Object getReference(Bean<?> bean);
	
	public abstract Object[] getInjectableReferenceForCallable(InjectionPoint ij, CreationalContext ctx);
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#createCreationalContext(javax
	 * .enterprise.context.spi.Contextual)
	 */
	@Override
	public <T> CreationalContext<T> createCreationalContext(Contextual<T> contextual) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#getBeans(java.lang.reflect.Type,
	 * java.lang.annotation.Annotation[])
	 */
	@Override
	public Set<Bean<?>> getBeans(Type beanType, Annotation... qualifiers) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.BeanManager#getBeans(java.lang.String)
	 */
	@Override
	public Set<Bean<?>> getBeans(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#getPassivationCapableBean(java
	 * .lang.String)
	 */
	@Override
	public Bean<?> getPassivationCapableBean(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.BeanManager#resolve(java.util.Set)
	 */
	@Override
	public <X> Bean<? extends X> resolve(Set<Bean<? extends X>> beans) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#validate(javax.enterprise.inject
	 * .spi.InjectionPoint)
	 */
	@Override
	public void validate(InjectionPoint injectionPoint) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.BeanManager#fireEvent(java.lang.Object,
	 * java.lang.annotation.Annotation[])
	 */
	@Override
	public void fireEvent(Object event, Annotation... qualifiers) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#resolveObserverMethods(java.lang
	 * .Object, java.lang.annotation.Annotation[])
	 */
	@Override
	public <T> Set<ObserverMethod<? super T>> resolveObserverMethods(T event, Annotation... qualifiers) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#resolveDecorators(java.util.Set,
	 * java.lang.annotation.Annotation[])
	 */
	@Override
	public List<Decorator<?>> resolveDecorators(Set<Type> types, Annotation... qualifiers) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#resolveInterceptors(javax.enterprise
	 * .inject.spi.InterceptionType, java.lang.annotation.Annotation[])
	 */
	@Override
	public List<Interceptor<?>> resolveInterceptors(InterceptionType type, Annotation... interceptorBindings) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.BeanManager#isScope(java.lang.Class)
	 */
	@Override
	public boolean isScope(Class<? extends Annotation> annotationType) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#isNormalScope(java.lang.Class)
	 */
	@Override
	public boolean isNormalScope(Class<? extends Annotation> annotationType) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#isPassivatingScope(java.lang.
	 * Class)
	 */
	@Override
	public boolean isPassivatingScope(Class<? extends Annotation> annotationType) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.BeanManager#isQualifier(java.lang.Class)
	 */
	@Override
	public boolean isQualifier(Class<? extends Annotation> annotationType) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#isInterceptorBinding(java.lang
	 * .Class)
	 */
	@Override
	public boolean isInterceptorBinding(Class<? extends Annotation> annotationType) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#isStereotype(java.lang.Class)
	 */
	@Override
	public boolean isStereotype(Class<? extends Annotation> annotationType) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#getInterceptorBindingDefinition
	 * (java.lang.Class)
	 */
	@Override
	public Set<Annotation> getInterceptorBindingDefinition(Class<? extends Annotation> bindingType) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#getStereotypeDefinition(java.
	 * lang.Class)
	 */
	@Override
	public Set<Annotation> getStereotypeDefinition(Class<? extends Annotation> stereotype) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#areQualifiersEquivalent(java.
	 * lang.annotation.Annotation, java.lang.annotation.Annotation)
	 */
	@Override
	public boolean areQualifiersEquivalent(Annotation qualifier1, Annotation qualifier2) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#areInterceptorBindingsEquivalent
	 * (java.lang.annotation.Annotation, java.lang.annotation.Annotation)
	 */
	@Override
	public boolean areInterceptorBindingsEquivalent(Annotation interceptorBinding1, Annotation interceptorBinding2) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#getQualifierHashCode(java.lang
	 * .annotation.Annotation)
	 */
	@Override
	public int getQualifierHashCode(Annotation qualifier) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#getInterceptorBindingHashCode
	 * (java.lang.annotation.Annotation)
	 */
	@Override
	public int getInterceptorBindingHashCode(Annotation interceptorBinding) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.BeanManager#getELResolver()
	 */
	@Override
	public ELResolver getELResolver() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#wrapExpressionFactory(javax.el
	 * .ExpressionFactory)
	 */
	@Override
	public ExpressionFactory wrapExpressionFactory(ExpressionFactory expressionFactory) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#createAnnotatedType(java.lang
	 * .Class)
	 */
	@Override
	public <T> AnnotatedType<T> createAnnotatedType(Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.inject.spi.BeanManager#createInjectionTarget(javax.
	 * enterprise.inject.spi.AnnotatedType)
	 */
	@Override
	public <T> InjectionTarget<T> createInjectionTarget(AnnotatedType<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#getInjectionTargetFactory(javax
	 * .enterprise.inject.spi.AnnotatedType)
	 */
	@Override
	public <T> InjectionTargetFactory<T> getInjectionTargetFactory(AnnotatedType<T> annotatedType) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#getProducerFactory(javax.enterprise
	 * .inject.spi.AnnotatedField, javax.enterprise.inject.spi.Bean)
	 */
	@Override
	public <X> ProducerFactory<X> getProducerFactory(AnnotatedField<? super X> field, Bean<X> declaringBean) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#getProducerFactory(javax.enterprise
	 * .inject.spi.AnnotatedMethod, javax.enterprise.inject.spi.Bean)
	 */
	@Override
	public <X> ProducerFactory<X> getProducerFactory(AnnotatedMethod<? super X> method, Bean<X> declaringBean) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#createBeanAttributes(javax.enterprise
	 * .inject.spi.AnnotatedType)
	 */
	@Override
	public <T> BeanAttributes<T> createBeanAttributes(AnnotatedType<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#createBeanAttributes(javax.enterprise
	 * .inject.spi.AnnotatedMember)
	 */
	@Override
	public BeanAttributes<?> createBeanAttributes(AnnotatedMember<?> type) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#createBean(javax.enterprise.inject
	 * .spi.BeanAttributes, java.lang.Class,
	 * javax.enterprise.inject.spi.InjectionTargetFactory)
	 */
	@Override
	public <T> Bean<T> createBean(BeanAttributes<T> attributes, Class<T> beanClass, InjectionTargetFactory<T> injectionTargetFactory) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#createBean(javax.enterprise.inject
	 * .spi.BeanAttributes, java.lang.Class,
	 * javax.enterprise.inject.spi.ProducerFactory)
	 */
	@Override
	public <T, X> Bean<T> createBean(BeanAttributes<T> attributes, Class<X> beanClass, ProducerFactory<X> producerFactory) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#createInjectionPoint(javax.enterprise
	 * .inject.spi.AnnotatedField)
	 */
	@Override
	public InjectionPoint createInjectionPoint(AnnotatedField<?> field) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#createInjectionPoint(javax.enterprise
	 * .inject.spi.AnnotatedParameter)
	 */
	@Override
	public InjectionPoint createInjectionPoint(AnnotatedParameter<?> parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.inject.spi.BeanManager#getExtension(java.lang.Class)
	 */
	@Override
	public <T extends Extension> T getExtension(Class<T> extensionClass) {
		// TODO Auto-generated method stub
		return null;
	}

}

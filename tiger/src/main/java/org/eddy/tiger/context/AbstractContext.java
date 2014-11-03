/**
 * 
 * @creatTime 上午10:56:43
 * @author Eddy
 */
package org.eddy.tiger.context;

import java.lang.annotation.Annotation;

import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;

import org.eddy.tiger.TigerBean;

/**
 * @author Eddy
 * 
 */
public abstract class AbstractContext implements Context {

	private Class<? extends Annotation> scop;

	/**
	 * 构造函数
	 * 
	 * @creatTime 上午11:07:09
	 * @author Eddy
	 */
	public AbstractContext() {
	}

	/**
	 * 构造函数
	 * 
	 * @creatTime 上午11:07:09
	 * @author Eddy
	 */
	public AbstractContext(Class<? extends Annotation> scop) {
		this.scop = scop;
	}

	/**
	 * 添加bean
	 * @param bean
	 * @creatTime 下午2:45:57
	 * @author Eddy
	 */
	public abstract void addBean(TigerBean<?> bean);

	/**
	 * 获取CreationalContext
	 * @return
	 * @creatTime 下午2:46:24
	 * @author Eddy
	 */
	public abstract CreationalContext<?> getCreationalContext();
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.context.spi.Context#getScope()
	 */
	@Override
	public Class<? extends Annotation> getScope() {
		return this.scop;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.context.spi.Context#isActive()
	 */
	@Override
	public boolean isActive() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.context.spi.Context#get(javax.enterprise.context.spi
	 * .Contextual, javax.enterprise.context.spi.CreationalContext)
	 */
	@Override
	public <T> T get(Contextual<T> contextual, CreationalContext<T> creationalContext) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}

/**
 * 
 * @creatTime 下午5:18:42
 * @author Eddy
 */
package org.eddy.tiger.context.impl;

import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.inject.Singleton;

import org.eddy.tiger.TigerBean;
import org.eddy.tiger.annotated.Request;
import org.eddy.tiger.context.AbstractContext;
import org.eddy.tiger.context.CreationalContextImpl;
import org.eddy.tiger.context.TigerCreationalContext;

/**
 * @author Eddy
 * 
 */
@SuppressWarnings("all")
public class RequestContext extends AbstractContext {

	private TigerCreationalContext<TigerBean<?>> context = new CreationalContextImpl<>(this);
	
	/**
	 * 构造函数
	 * @creatTime 下午1:14:54
	 * @author Eddy
	 */
	public RequestContext() {
		super(Request.class);
	}
	
	/* (non-Javadoc)
	 * @see javax.enterprise.context.spi.Context#get(javax.enterprise.context.spi.Contextual)
	 */
	@Override
	public <T> T get(Contextual<T> contextual) {
		return contextual.create((CreationalContext) context);
	}

	/* (non-Javadoc)
	 * @see org.eddy.tiger.context.AbstractContext#addBean(javax.enterprise.inject.spi.Bean)
	 */
	@Override
	public void addBean(TigerBean<?> bean) {
		context.push((TigerBean<?>) bean);
		
	}

	/* (non-Javadoc)
	 * @see org.eddy.tiger.context.AbstractContext#getCreationalContext()
	 */
	@Override
	public CreationalContext<?> getCreationalContext() {
		return this.context;
	}
}

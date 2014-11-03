/**
 * 
 * @creatTime 下午1:07:37
 * @author Eddy
 */
package org.eddy.tiger.context.impl;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.inject.Singleton;

import org.eddy.tiger.TigerBean;
import org.eddy.tiger.context.AbstractContext;
import org.eddy.tiger.context.CreationalContextImpl;
import org.eddy.tiger.context.TigerCreationalContext;

/**
 * @author Eddy
 *
 */
@SuppressWarnings("all")
public class SingletonContext extends AbstractContext {

	private TigerCreationalContext<TigerBean<?>> context = new CreationalContextImpl<>(this);
	
	/**
	 * 实例缓存对象
	 */
	private Map<Contextual, Object> cache = new HashMap<>();
	
	/**
	 * 构造函数
	 * @creatTime 下午1:14:54
	 * @author Eddy
	 */
	public SingletonContext() {
		super(Singleton.class);
	}
	
	/* (non-Javadoc)
	 * @see javax.enterprise.context.spi.Context#get(javax.enterprise.context.spi.Contextual)
	 */
	@Override
	public <T> T get(Contextual<T> contextual) {
		T result = (T) cache.get(contextual);
		if (result == null) {
			result = contextual.create((CreationalContext) context);
			cache.put(contextual, result);
		}
		return result;
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

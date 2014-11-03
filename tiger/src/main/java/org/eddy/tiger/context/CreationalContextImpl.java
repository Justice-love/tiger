/**
 * 
 * @creatTime 上午11:35:28
 * @author Eddy
 */
package org.eddy.tiger.context;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import org.eddy.tiger.TigerBean;

/**
 * @author Eddy
 * 
 */
public class CreationalContextImpl<T> implements TigerCreationalContext<T> {

	private Set<T> incompleteInstances = new HashSet<>();

	private AbstractContext context;
	
	/**
	 * 构造函数
	 * @creatTime 上午9:18:42
	 * @author Eddy
	 */
	public CreationalContextImpl(AbstractContext context) {
		this.context = context;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.enterprise.context.spi.CreationalContext#push(java.lang.Object)
	 */
	@Override
	public void push(T incompleteInstance) {
		incompleteInstances.add(incompleteInstance);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.enterprise.context.spi.CreationalContext#release()
	 */
	@Override
	public void release() {
		this.incompleteInstances.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eddy.tiger.context.TigerCreationalContext#get(java.lang.String)
	 */
	@Override
	public T get(String name) {
		for (T bean : incompleteInstances) {
			if (((TigerBean<?>) bean).isRight(name, null)) {
				return bean;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eddy.tiger.context.TigerCreationalContext#get(java.lang.String,
	 * java.lang.reflect.Type)
	 */
	@Override
	public T get(String name, Type type) {
		for (T bean : incompleteInstances) {
			if (((TigerBean<?>) bean).isRight(name, type)) {
				return bean;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eddy.tiger.context.TigerCreationalContext#getContext()
	 */
	@Override
	public AbstractContext getContext() {
		return this.context;
	}

}

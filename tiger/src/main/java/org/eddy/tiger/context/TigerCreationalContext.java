/**
 * 
 * @creatTime 上午11:50:32
 * @author Eddy
 */
package org.eddy.tiger.context;

import java.lang.reflect.Type;

import javax.enterprise.context.spi.CreationalContext;

/**
 * @author Eddy
 *
 */
public interface TigerCreationalContext<T> extends CreationalContext<T> {
	
	T get(String name);
	
	T get(String name, Type type);
	
	AbstractContext getContext();
}

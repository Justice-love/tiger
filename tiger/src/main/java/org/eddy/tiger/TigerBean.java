/**
 * 
 * @creatTime 上午11:38:55
 * @author Eddy
 */
package org.eddy.tiger;

import java.lang.reflect.Type;

import javax.enterprise.inject.spi.Bean;

import org.eddy.tiger.point.ConstructorInjectionPoint;

/**
 * @author Eddy
 *
 */
public interface TigerBean<T> extends Bean<T> {

	boolean isRight(String name, Type type);
	
	boolean isConstructor();
	
	ConstructorInjectionPoint getConstructorInjectionPoint();
}

/**
 * 
 * @creatTime 上午8:47:25
 * @author Eddy
 */
package org.eddy.tiger.annotated;

import java.lang.reflect.Type;
import java.util.Set;

import javax.enterprise.inject.spi.AnnotatedCallable;

/**
 * @author Eddy
 *
 */
public abstract class AbstractAnnotatedCallable<X> implements AnnotatedCallable<X> {

	public abstract Set<Type> getBaseTypeForSet();
	
}

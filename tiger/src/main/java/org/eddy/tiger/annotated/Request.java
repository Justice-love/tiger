/**
 * 
 * @creatTime 下午5:24:43
 * @author Eddy
 */
package org.eddy.tiger.annotated;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Scope;

/**
 * @author Eddy
 *
 */
@Scope
@Documented
@Retention(RUNTIME)
@Target(ElementType.TYPE)
public @interface Request {

}

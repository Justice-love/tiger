/**
 * 
 * @creatTime 下午5:42:05
 * @author Eddy
 */
package org.eddy.tiger.point;

import java.lang.reflect.Member;

import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.AnnotatedMethod;

/**
 * @author Eddy
 *
 */
public class MethodInjectionPoint extends AbstractInjectionPoint {

	/**
	 * 构造函数
	 * @param annotated
	 * @creatTime 下午5:42:07
	 * @author Eddy
	 */
	public MethodInjectionPoint(Annotated annotated) {
		super(annotated);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.InjectionPoint#getMember()
	 */
	@Override
	public Member getMember() {
		return ((AnnotatedMethod<?>)super.annotated).getJavaMember();
	}

}

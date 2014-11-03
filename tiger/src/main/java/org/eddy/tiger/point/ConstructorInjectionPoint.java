/**
 * 
 * @creatTime 下午5:42:49
 * @author Eddy
 */
package org.eddy.tiger.point;

import java.lang.reflect.Member;

import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.AnnotatedConstructor;

/**
 * @author Eddy
 *
 */
public class ConstructorInjectionPoint extends AbstractInjectionPoint {

	/**
	 * 构造函数
	 * @param annotated
	 * @creatTime 下午5:42:51
	 * @author Eddy
	 */
	public ConstructorInjectionPoint(Annotated annotated) {
		super(annotated, AbstractInjectionPoint.CLOSED);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.InjectionPoint#getMember()
	 */
	@Override
	public Member getMember() {
		return ((AnnotatedConstructor<?>)super.annotated).getJavaMember();
	}

}

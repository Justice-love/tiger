/**
 * 
 * @creatTime 下午5:39:40
 * @author Eddy
 */
package org.eddy.tiger.point;

import java.lang.reflect.Member;

import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.AnnotatedField;

/**
 * @author Eddy
 *
 */
public class FieldInjectionPoint extends AbstractInjectionPoint {

	/**
	 * 构造函数
	 * @param annotated
	 * @creatTime 下午5:39:44
	 * @author Eddy
	 */
	public FieldInjectionPoint(Annotated annotated) {
		super(annotated);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see javax.enterprise.inject.spi.InjectionPoint#getMember()
	 */
	@Override
	public Member getMember() {
		return ((AnnotatedField<?>)super.annotated).getJavaMember();
	}
	
	

}

/**
 * 
 * @creatTime 下午3:15:07
 * @author Eddy
 */
package tiger.test.methodloop;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author Eddy
 * 
 */
@Named("b")
@Singleton
@SuppressWarnings("all")
public class B {

	private C c;
	private A a;

	@Inject
	public void setC(C c, A a) {
		this.c = c;
		this.a = a;
	}

}

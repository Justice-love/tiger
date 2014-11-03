/**
 * 
 * @creatTime 下午3:15:20
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
@Named("c")
@Singleton
@SuppressWarnings("all")
public class C {

	private A a;
	private B b;

	@Inject
	public void setA(A a, B b) {
		this.a = a;
		this.b = b;
	}

}

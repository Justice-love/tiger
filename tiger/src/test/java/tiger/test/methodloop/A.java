/**
 * 
 * @creatTime 下午3:15:00
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
@Named("a")
@Singleton
@SuppressWarnings("all")
public class A {

	private B b;
	private C c;

	@Inject
	public void setB(B b, C c) {
		this.b = b;
		this.c = c;
	}

}

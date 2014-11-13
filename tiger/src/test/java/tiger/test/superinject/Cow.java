/**
 * 
 * @creatTime 下午8:11:39
 * @author Eddy
 */
package tiger.test.superinject;

import javax.inject.Named;

/**
 * @author Eddy
 *
 */
@Named("cow")
public class Cow {

	public void cow() {
		System.out.println("cow");
	}
}

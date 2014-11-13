/**
 * 
 * @creatTime 下午8:11:12
 * @author Eddy
 */
package tiger.test.superinject;

import javax.inject.Named;

/**
 * @author Eddy
 *
 */
@Named("phoenix")
public class Phoenix extends Animal {

	public void phoenix() {
		cow.cow();
	}
}

/**
 * 
 * @creatTime 下午9:05:33
 * @author Eddy
 */
package tiger.test.field;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author Eddy
 *
 */
@Named("sheep")
@Singleton
public class Sheep {

	public void sheep() {
		System.out.println("sheep");
	}
}

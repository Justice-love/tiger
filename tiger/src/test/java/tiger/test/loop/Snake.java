/**
 * 
 * @creatTime 上午10:22:57
 * @author Eddy
 */
package tiger.test.loop;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author Eddy
 *
 */
@Named("snake")
@Singleton
public class Snake {

	@Inject
	private Bear bear;
	
	public void snake() {
		System.out.println("bear is not null: " + (bear == null));
		System.out.println("snake");
	}
}

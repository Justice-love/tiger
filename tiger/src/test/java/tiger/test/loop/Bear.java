/**
 * 
 * @creatTime 上午10:22:38
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
@Named("bear")
@Singleton
public class Bear {

	@Inject
	private Snake snake;
	
	public void bear() {
		System.out.println("snake is not null: " + (snake == null));
		System.out.println("bear");
		snake.snake();
	}
}

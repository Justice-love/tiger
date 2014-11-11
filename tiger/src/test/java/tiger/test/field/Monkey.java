/**
 * 
 * @creatTime 下午9:03:24
 * @author Eddy
 */
package tiger.test.field;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author Eddy
 *
 */
@Named("monkey")
@Singleton
public class Monkey {

	@Named("sheep")
	Sheep sheep;
	
//	@Named("sheep1")
//	Sheep sheep1;
	
	public void monkey() {
		sheep.sheep();
	}
}

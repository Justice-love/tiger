/**
 * 
 * @creatTime 上午11:49:45
 * @author Eddy
 */
package tiger.test.deep;

import javax.inject.Inject;

/**
 * @author Eddy
 *
 */
public class Little {

	@Inject
	private Horse horse;
	
	public void little() {
		horse.horse();
		System.out.println("little");
	}
}

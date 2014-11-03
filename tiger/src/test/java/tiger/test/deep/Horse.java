/**
 * 
 * @creatTime 上午11:50:23
 * @author Eddy
 */
package tiger.test.deep;

import javax.inject.Inject;

/**
 * @author Eddy
 *
 */
public class Horse {

	@Inject
	private Dep dep;
	
	public void horse() {
		dep.dep();
		System.out.println("horse");
	}
}

/**
 * 
 * @creatTime 下午5:56:39
 * @author Eddy
 */
package tiger.test.request;

import javax.inject.Named;

import org.eddy.tiger.annotated.Request;


/**
 * @author Eddy
 *
 */
@Request
@Named("cat")
public class Cat {

	public void cat() {
		System.out.println("cat");
	}
}

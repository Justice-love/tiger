/**
 * 
 * @creatTime 下午8:26:49
 * @author Eddy
 */
package tiger.test.request;

import javax.inject.Named;

import org.eddy.tiger.annotated.Request;

/**
 * @author Eddy
 *
 */
@Named("dog")
@Request
public class Dog {

	
	public void dog() {
		System.out.println("dog");
	}
}

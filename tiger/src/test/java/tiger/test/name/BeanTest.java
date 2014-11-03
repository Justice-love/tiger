/**
 * 
 * @creatTime 下午4:13:55
 * @author Eddy
 */
package tiger.test.name;

import org.eddy.tiger.TigerBeanManage;
import org.eddy.tiger.impl.TigerBeanManageImpl;
import org.junit.Test;

/**
 * @author Eddy
 *
 */
public class BeanTest {

	@Test
	public void test() {
		TigerBeanManage manage = new TigerBeanManageImpl();
		manage.createBean(NameA.class);
		manage.createBean(NameB.class);
	}
}

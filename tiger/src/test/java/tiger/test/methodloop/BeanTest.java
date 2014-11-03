/**
 * 
 * @creatTime 下午3:21:45
 * @author Eddy
 */
package tiger.test.methodloop;

import org.eddy.tiger.TigerBean;
import org.eddy.tiger.TigerBeanManage;
import org.eddy.tiger.impl.TigerBeanManageImpl;
import org.junit.Test;

/**
 * @author Eddy
 *
 */
@SuppressWarnings("all")
public class BeanTest {

	@Test
	public void test() {
		TigerBeanManage manage = new TigerBeanManageImpl();
		TigerBean<A> aBean = manage.createBean(A.class);
		manage.createBean(B.class);
		manage.createBean(C.class);
		A a = manage.getReference(aBean);
	}
}

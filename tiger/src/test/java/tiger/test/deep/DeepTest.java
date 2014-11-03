/**
 * 
 * @creatTime 上午11:46:36
 * @author Eddy
 */
package tiger.test.deep;

import org.eddy.tiger.TigerBean;
import org.eddy.tiger.TigerBeanManage;
import org.eddy.tiger.impl.TigerBeanManageImpl;
import org.junit.Test;

/**
 * @author Eddy
 *
 */
public class DeepTest {

	@Test
	public void test() {
		TigerBeanManage manage = new TigerBeanManageImpl();
		TigerBean<Little> littleBean = manage.createBean(Little.class);
		manage.createBean(Dep.class);
		manage.createBean(Horse.class);
		Little little = manage.getReference(littleBean);
		little.little();
	}
}

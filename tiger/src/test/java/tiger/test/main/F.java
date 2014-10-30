/**
 * 
 * @creatTime 上午10:15:39
 * @author Eddy
 */
package tiger.test.main;

import javax.inject.Inject;
import javax.inject.Named;

import org.eddy.tiger.TigerBean;
import org.eddy.tiger.TigerBeanManage;
import org.eddy.tiger.impl.TigerBeanManageImpl;
import org.junit.Test;

/**
 * @author Eddy
 *
 */
@Named("f")
public class F {

	@Inject
	public Depence depence;
	
	private Depence depance2;
	
	
	
	@Test
	public void depence() {
		TigerBeanManage manage = TigerBeanManageImpl.getInstance();
		TigerBean<F> bean = manage.createBean(F.class);
		manage.createBean(Depence.class);
		F main = (F) manage.getReference(bean);
		main.getDepance2().test();
		main.depence.test();
	}
	
	public Depence getDepance2() {
		return depance2;
	}

	@Inject
	public void setDepance2(Depence depance2) {
		this.depance2 = depance2;
	}
	
}

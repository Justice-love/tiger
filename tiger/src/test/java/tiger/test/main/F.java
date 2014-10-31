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

	private Depence depance3;

	private Pig pig;

	@Test
	public void depence() {
		TigerBeanManage manage = TigerBeanManageImpl.getInstance();
		TigerBean<F> bean = manage.createBean(F.class);
		manage.createBean(Depence.class);
		manage.createBean(Pig.class);
		F main = (F) manage.getReference(bean);
		main.getDepance2().test();
		main.depence.test();
		main.getDepance3().test();
		main.getPig().pig();
	}

	public Depence getDepance2() {
		return depance2;
	}

	@Inject
	public void setDepance2(Depence depance2) {
		this.depance2 = depance2;
	}
	
	@Inject
	public void setMultiParam(Depence depence, Pig pig) {
		this.depance3 = depence;
		this.pig = pig;
	}

	public Depence getDepance3() {
		return depance3;
	}

	public void setDepance3(Depence depance3) {
		this.depance3 = depance3;
	}

	public Pig getPig() {
		return pig;
	}

	public void setPig(Pig pig) {
		this.pig = pig;
	}

}

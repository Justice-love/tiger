/**
 * 
 * @creatTime 下午6:05:16
 * @author Eddy
 */
package tiger.test.main;

import javax.inject.Inject;
import javax.inject.Named;

import org.eddy.tiger.TigerBean;
import org.eddy.tiger.TigerBeanManage;
import org.eddy.tiger.impl.TigerBeanManageImpl;

/**
 * @author Eddy
 *
 */
@Named("tiger")
public class Tiger {
	
	private Depence depance3;
	
	private Pig pig;
	
	/**
	 * 构造函数
	 * @creatTime 下午6:00:19
	 * @author Eddy
	 */
	@Inject
	public Tiger(Depence depance3, Pig pig) {
		this.depance3 = depance3;
		this.pig = pig;
		System.out.println("invoke constructor");
	}
	
	public static void main(String[] args) {
		TigerBeanManage manage = TigerBeanManageImpl.getInstance();
		TigerBean<Tiger> bean = manage.createBean(Tiger.class);
		manage.createBean(Depence.class);
		manage.createBean(Pig.class);
		Tiger main = (Tiger) manage.getReference(bean);
		main.getDepance3().test();
		main.getPig().pig();
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

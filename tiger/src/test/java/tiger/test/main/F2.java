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
@Named("f2")
public class F2 {
	
	private Depence depance3;
	
	/**
	 * 构造函数
	 * @creatTime 下午6:00:19
	 * @author Eddy
	 */
	@Inject
	public F2(Depence depance3) {
		this.depance3 = depance3;
		System.out.println("invoke constructor");
	}
	
	public static void main(String[] args) {
		TigerBeanManage manage = TigerBeanManageImpl.getInstance();
		TigerBean<F2> bean = manage.createBean(F2.class);
		manage.createBean(Depence.class);
		F2 main = (F2) manage.getReference(bean);
		main.getDepance3().test();
	}


	public Depence getDepance3() {
		return depance3;
	}

	public void setDepance3(Depence depance3) {
		this.depance3 = depance3;
	}
	
}

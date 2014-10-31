/**
 * 
 * @creatTime 下午5:49:26
 * @author Eddy
 */
package tiger.test.request;

import org.eddy.tiger.TigerBean;
import org.eddy.tiger.TigerBeanManage;
import org.eddy.tiger.impl.TigerBeanManageImpl;

/**
 * @author Eddy
 * 
 */
public class RequestTest {

	
	public static void main(String[] args) {
		TigerBeanManage manage = new TigerBeanManageImpl();
		Thread t1 = new RequestThread(manage);
		Thread t2 = new RequestThread(manage);
		t1.start();
		t2.start();
	}
	
	static class RequestThread extends Thread {
		
		private TigerBeanManage manage;
		
		/**
		 * 构造函数
		 * @creatTime 下午5:54:23
		 * @author Eddy
		 */
		public RequestThread(TigerBeanManage manage) {
			this.manage = manage;
		}
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			TigerBean<Cat> bean = manage.createBean(Cat.class);
		}
	}
}

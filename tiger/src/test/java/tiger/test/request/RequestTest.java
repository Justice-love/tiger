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

	static TigerBean<Cat> bean;
	
	static TigerBean<Dog> dogB;
	
	public static void main(String[] args) throws InterruptedException {
		TigerBeanManage manage = new TigerBeanManageImpl();
		bean = manage.createBean(Cat.class);
		dogB = manage.createBean(Dog.class);
		Thread t1 = new RequestThread(manage);
		Thread t2 = new RequestThread(manage);
		t1.start();
		Thread.sleep(1000);
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
			Cat cat = manage.getReference(bean);
			Dog dog = manage.getReference(dogB);
			Dog dog2 = manage.getReference(dogB);
			System.out.println(dog == dog2);
			cat.cat();
			dog.dog();
		}
	}
}

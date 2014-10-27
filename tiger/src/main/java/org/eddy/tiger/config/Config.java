/**
 * 
 * @creatTime 上午11:28:55
 * @author Eddy
 */
package org.eddy.tiger.config;

import org.eddy.tiger.TigerBean;

/**
 * @author Eddy
 *
 */
public interface Config {

	/**
	 * 根据class返回对应的Bean
	 * @param glass
	 * @return
	 * @creatTime 上午11:59:16
	 * @author Eddy
	 */
	<T> TigerBean<T> createBean(Class<T> glass);
}

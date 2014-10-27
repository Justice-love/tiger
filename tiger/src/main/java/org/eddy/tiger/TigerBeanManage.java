/**
 * 
 * @creatTime 下午2:22:31
 * @author Eddy
 */
package org.eddy.tiger;

import javax.enterprise.inject.spi.BeanManager;

import org.eddy.tiger.config.Config;

/**
 * @author Eddy
 *
 */
public interface TigerBeanManage extends BeanManager {

	Config getConfig();
}

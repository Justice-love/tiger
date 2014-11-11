/**
 * 
 * @creatTime 下午7:46:31
 * @author Eddy
 */
package org.eddy.tiger.annotated;

/**
 * @author Eddy
 *
 */
public interface AnnotatedName {

	/**
	 * 获取注入点名称
	 * @return
	 * @creatTime 下午7:47:29
	 * @author Eddy
	 */
	public String getAnnotatedName();
	
	/**
	 * 设置注入点名称
	 * @param name
	 * @creatTime 下午7:47:32
	 * @author Eddy
	 */
	public void setAnnotatedName(String name);
	
	/**
	 * 是否添加了{@code Named}}注解
	 * @return true:定义了名称； false：未定义名称
	 * @creatTime 下午8:15:41
	 * @author Eddy
	 */
	public boolean isNamed();
}

/**
 * 
 * @creatTime 下午2:10:28
 * @author Eddy
 */
package org.eddy.tiger.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Eddy
 * 
 */
public class Reflects {

	/**
	 * 获取当前class及父class的属性列表
	 * 
	 * @param glass
	 * @return
	 * @creatTime 下午2:31:34
	 * @author Eddy
	 */
	public static Set<Field> getFields(Class<?> glass) {
		if (null == glass)
			throw new IllegalArgumentException("glass 为空!");
		Set<Field> result = new HashSet<>(Arrays.asList(glass.getDeclaredFields()));
		Class<?> currentClass = glass.getSuperclass();
		while (currentClass != null && !currentClass.equals(Object.class)) {
			result.addAll(Arrays.asList(currentClass.getDeclaredFields()));
			currentClass = currentClass.getSuperclass();
		}
		return result;
	}

	/**
	 * 获取当前class及父class的构造器列表
	 * @param glass
	 * @return
	 * @creatTime 下午2:57:50
	 * @author Eddy
	 */
	public static Set<Constructor<?>> getConstructors(Class<?> glass) {
		if (null == glass)
			throw new IllegalArgumentException("glass 为空!");
		Set<Constructor<?>> result = new HashSet<>(Arrays.asList(glass.getDeclaredConstructors()));
		Class<?> currentClass = glass.getSuperclass();
		while (currentClass != null && !currentClass.equals(Object.class)) {
			result.addAll(Arrays.asList(currentClass.getDeclaredConstructors()));
			currentClass = currentClass.getSuperclass();
		}
		return result;
	}
	
	/**
	 * 获取当前class及父class的方法列表
	 * @param glass
	 * @return
	 * @creatTime 下午3:02:00
	 * @author Eddy
	 */
	public static Set<Method> getMethods(Class<?> glass) {
		if (null == glass)
			throw new IllegalArgumentException("glass 为空!");
		Set<Method> result = new HashSet<>(Arrays.asList(glass.getDeclaredMethods()));
		Class<?> currentClass = glass.getSuperclass();
		while (currentClass != null && !currentClass.equals(Object.class)) {
			result.addAll(Arrays.asList(currentClass.getDeclaredMethods()));
			currentClass = currentClass.getSuperclass();
		}
		return result;
	}

}

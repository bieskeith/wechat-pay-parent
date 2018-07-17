package com.biminds.framework.mvc;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
* <p>Title: SpringContextUtils</p>
* <p>Description: </p>
* @Project runlin-framework
* @Package com.runlin.framework.mvc
* @author Kevin Lv 
* @date 2016年11月11日 下午2:36:56
* @version V1.0
 */
public class ApplicationContextUtils implements ApplicationContextAware {

	private static ApplicationContext applicationContext; // Spring应用上下文环境

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ApplicationContextUtils.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	//根据bean name 获取实例
	  public static Object getBeanByName(String beanName) {
	    if (beanName == null || applicationContext == null) {
	      return null;
	    }
	    return applicationContext.getBean(beanName);
	  }

}
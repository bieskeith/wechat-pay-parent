package com.biminds.framework.util;

/**
 * ExceptionUtils
 * 
 * @author 何珏 2014-6-26
 */
public class ExceptionUtils {

	/**
	 * 获取异常名称
	 * 
	 * @param ex Exception
	 * @return 异常名称
	 * @author 何珏 2014-6-26
	 */
	public static String getExceptionName(Exception ex) {
		return ex.getClass().getSimpleName();
	}

}

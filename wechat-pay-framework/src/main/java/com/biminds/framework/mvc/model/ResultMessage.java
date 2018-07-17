package com.biminds.framework.mvc.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 错误消息
 * 
 * @author 何珏 2014-6-25
 */
public class ResultMessage {

	private Boolean success = true; // 请求是否成功

	//	private Boolean isError; // 是否是错误信息

	private Boolean isException; // 是否抛出异常，抛出异常则success为false，优先级大于success

	private Boolean isSessionTimeout; // session是否失效

	private String errorCode; // 错误编码

	private String exceptionName; // 异常名称

	private String exceptionDetails; // 异常详细信息

	private String message; // 消息

	private Map<String, Object> paramMap = new HashMap<String, Object>();//参数map

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	/**
	 * @return the success
	 */
	public Boolean getSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(Boolean success) {
		this.success = success;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the exceptionName
	 */
	public String getExceptionName() {
		return exceptionName;
	}

	/**
	 * @param exceptionName the exceptionName to set
	 */
	public void setExceptionName(String exceptionName) {
		this.exceptionName = exceptionName;
	}

	/**
	 * @return the exceptionDetails
	 */
	public String getExceptionDetails() {
		return exceptionDetails;
	}

	/**
	 * @param exceptionDetails the exceptionDetails to set
	 */
	public void setExceptionDetails(String exceptionDetails) {
		this.exceptionDetails = exceptionDetails;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the isSessionTimeout
	 */
	public Boolean getIsSessionTimeout() {
		return isSessionTimeout;
	}

	/**
	 * @param isSessionTimeout the isSessionTimeout to set
	 */
	public void setIsSessionTimeout(Boolean isSessionTimeout) {
		this.isSessionTimeout = isSessionTimeout;
	}

	public Boolean getIsException() {
		return isException;
	}

	public void setIsException(Boolean isException) {
		this.isException = isException;
	}

}

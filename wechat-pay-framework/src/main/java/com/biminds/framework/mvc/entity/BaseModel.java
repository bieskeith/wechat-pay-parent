/**
 * $Id:$
 * Copyright 2009-2014 河北联智信息科技有限公司. All rights reserved.
 */
package com.biminds.framework.mvc.entity;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

/**
 * BaseModel
 * 
 * @author 何珏 2014-11-3
 */
public class BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 是否成功 */
	private boolean success = true;

	/** 消息 */
	private String message;

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
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(this);
		} catch (Exception ex) {
			return super.toString();
		}
	}
}

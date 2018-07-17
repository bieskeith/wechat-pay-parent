package com.biminds.framework.excepetion;

/**
 * Token验证失败异常
 * 
 * @author 何珏 2014-8-14
 */
public class TokenInvalidException extends RuntimeException {

	private static final long serialVersionUID = -6311165700382903956L;

	public TokenInvalidException(String message) {
		super(message);
	}

}

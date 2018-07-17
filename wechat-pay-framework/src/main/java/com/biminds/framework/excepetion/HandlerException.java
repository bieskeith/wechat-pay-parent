package com.biminds.framework.excepetion;

/**
 * 自定义异常
 *
 * @author 何珏 2014-7-15
 */
public class HandlerException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误编码
     */
    private String errorCode;

    /**
     * 构造方法
     *
     * @param message
     */
    public HandlerException(String message) {
        super(message);
    }

    /**
     * 构造方法
     *
     * @param errorCode
     * @param message
     */
    public HandlerException(String errorCode, String message) {
        super(message);
        setErrorCode(errorCode);
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

}

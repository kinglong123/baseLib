package com.kinglong.baseapp.mybaseapp.view.base.util;

/**
 * BizException
 *
 * @author yangz
 * @version 2014/11/29
 */
public class BizException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "操作失败，请重试";

    public static final int SENSITIVE_WORD_ERROR_CODE = 50004;


    private int code;

    private String message;


    public BizException() {
    }


    public BizException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
        this.message = detailMessage;
    }

    public BizException(String detailMessage) {
        super(detailMessage);
        this.message = detailMessage;
    }

    public BizException(Throwable throwable) {
        super(throwable);
    }

    public BizException(int code, String message) {
        this.code = code;
        this.message = message == null ? DEFAULT_MESSAGE : message;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

package com.kinglong.baseapp.mybaseapp.view.base.util;

/**
 * EmptyDataException
 *
 * @author yangz
 * @version 2015/9/16
 */
public class EmptyDataException extends BizException{
    private static final String EMPTYDATA_MESSAGE = "数据请求失败";
    public EmptyDataException() {
        super(EMPTYDATA_MESSAGE);
    }

    public EmptyDataException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public EmptyDataException(String detailMessage) {
        super(detailMessage);
    }

    public EmptyDataException(Throwable throwable) {
        super(throwable);
    }
}

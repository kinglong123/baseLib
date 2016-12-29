package com.kinglong.baseapp.mybaseapp.view.base.util;

/**
 * Created by lanjl on 2016/12/19.
 */

public class ResultException extends RuntimeException {
    private int errCode = 0;

    public ResultException(int errCode, String msg) {
        super(msg);
        this.errCode = errCode;
    }

    public int getErrCode() {
        return errCode;
    }
}

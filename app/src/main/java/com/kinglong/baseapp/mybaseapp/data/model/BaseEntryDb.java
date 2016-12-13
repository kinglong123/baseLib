package com.kinglong.baseapp.mybaseapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by lanjl on 2016/12/3.
 */

public class BaseEntryDb<T> implements Serializable {

    @SerializedName("Code")
    public int code;


    @SerializedName("Message")
    public String message;


    @SerializedName("Detail")
    public String detail;

    @SerializedName("Data")
    private T data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

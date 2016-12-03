package com.kinglong.baseapp.mybaseapp.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by lanjl on 2016/12/3.
 */
public class BaseEntryByJson implements Serializable {//<T>
    @JsonProperty("Code")
    public int code;

    @JsonProperty("Message")
    public String message;

    @JsonProperty("Detail")
    public String detail;
   // @SerializedName("Data")
//    private T data;


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
}

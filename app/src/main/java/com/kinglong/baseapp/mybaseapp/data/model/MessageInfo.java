package com.kinglong.baseapp.mybaseapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Bryce on 2015/6/17.
 */
public class MessageInfo implements Serializable {

    @SerializedName("TotalCount")
    private int           totalCount;
    @SerializedName("Items")
    private List<Message> messageList;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
}

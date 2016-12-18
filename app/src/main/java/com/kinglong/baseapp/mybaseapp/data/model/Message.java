package com.kinglong.baseapp.mybaseapp.data.model;


import com.google.gson.annotations.SerializedName;

import com.kinglong.baseapp.mybaseapp.db.DbFlowDataBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

/**
 * Created by Bryce on 2015/6/16.
 */
@Table(database = DbFlowDataBase.class)
public class Message extends BaseModel implements Serializable {

    public static final String NAME = "Message";
    @Column
    @PrimaryKey(autoincrement = true)
    long did;

    @Column(name = "uid")
    private String    uid;
    @SerializedName("Id")
    @Column
    private long    msgId;
    @SerializedName("Title")
    @Column
    private String title;
    @SerializedName("CreateTime")
    @Column
    private String createTime;
    @SerializedName("Status")
    @Column
    private int     status;
    @SerializedName("MsgType")
    @Column
    private int     type;
    @Column
    @SerializedName("ObjectId")
    private int     objectId;

//    @Column(isJsonText = true)
//    @SerializedName("Data")
//    private MsgData data;
//    @Column
//    private String projectId;

//    public String getProjectId() {
//        return projectId;
//    }
//
//    public void setProjectId(String projectId) {
//        this.projectId = projectId;
//    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

//    public MsgData getData() {
//        return data;
//    }
//
//    public void setData(MsgData data) {
//        this.data = data;
//    }
}

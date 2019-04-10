package com.kinglong.baseapp.mybaseapp.view.downnew;

/**
 * Created by lanjl on 2019/4/9.
 */
public enum ServiceType {
    LOCAL(1),
    REMOTE(2);

    private int mType;

    private ServiceType(int type) {
        mType = type;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }
}

package com.kinglong.baseapp.mybaseapp.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by lanjl on 2016/12/12.
 */
@Database(name = DbFlowDataBase.NAME, version = DbFlowDataBase.VERSION)
public class DbFlowDataBase {
    static final String NAME = "DbFlowDataBase";
    static final int VERSION = 3;
}

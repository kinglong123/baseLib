package com.konglong.db.sqlbritehelper.module.manager;

import com.konglong.db.sqlbritehelper.module.TodoDbComponent;
import com.squareup.sqlbrite.BriteDatabase;

import javax.inject.Inject;

/**
 * Created by lanjl on 2016/12/13.
 */

public class DbFlowManager {
    private static DbFlowManager instance = null;

    public static DbFlowManager getInstance() {

        if (instance == null) {//第一个用来提供速度，被进synchronized 等着
            synchronized (DbFlowManager.class) {
                if (instance == null) {//这是必须的，两个都会走到这里时，要为第二个来的判断下
                    instance = new DbFlowManager();
                }
            }
        }
        return instance;
    }

    @Inject
    BriteDatabase db;



    public DbFlowManager() {
        TodoDbComponent.Instance.get().inject(this);
        //  LibsComponent.Instance.get().inject(this);
    }

    public BriteDatabase getDb() {
        return db;
    }
}

package com.konglong.db.sqlbritehelper.module.init;

import com.konglong.db.sqlbritehelper.module.DaggerTodoDbComponent;
import com.konglong.db.sqlbritehelper.module.DbFlowModule;
import com.konglong.db.sqlbritehelper.module.TodoDbComponent;
import com.konglong.db.sqlbritehelper.module.manager.DbFlowManager;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.squareup.sqlbrite.BriteDatabase;

/**
 * Created by lanjl on 2016/12/14.
 */

public class SqlbriteHelper {



     public static void init(String name){
         DatabaseDefinition databaseDefinition = FlowManager.getDatabase(name);
         TodoDbComponent.Instance.init(
                 DaggerTodoDbComponent.builder().dbFlowModule(new DbFlowModule(databaseDefinition)).build());
     }


    public static  BriteDatabase getBriteDatabase(){
       return new DbFlowManager().getDb();
    }

}

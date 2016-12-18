package com.konglong.db.sqlbritehelper.module;

import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.structure.database.OpenHelper;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import android.database.sqlite.SQLiteOpenHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.schedulers.Schedulers;

/**
 * Created by lanjl on 2016/12/13.
 */

@Module
public class DbFlowModule {
    DatabaseDefinition mDatabaseDefinition;

   public DbFlowModule(DatabaseDefinition databaseDefinition){
       mDatabaseDefinition = databaseDefinition;
   }


    @Provides
    @Singleton
    SqlBrite provideSqlBrite() {
        return SqlBrite.create(new SqlBrite.Logger() {
            @Override
            public void log(String message) {
//                Timber.tag("Database").v(message);
                System.out.println(message);
            }
        });
    }

    @Provides
    @Singleton
    BriteDatabase provideDatabase(SqlBrite sqlBrite, SQLiteOpenHelper helper) {
        BriteDatabase db = sqlBrite.wrapDatabaseHelper(helper, Schedulers.io());
        db.setLoggingEnabled(true);
        return db;
    }


    @Provides
    @Singleton
    SQLiteOpenHelper provideOpenHelper() {
        SQLiteOpenHelper helper = null;
        OpenHelper openHelper = mDatabaseDefinition.getHelper();
        if (openHelper instanceof SQLiteOpenHelper) {
            helper = (SQLiteOpenHelper) openHelper;
        }
        return helper;
    }
}

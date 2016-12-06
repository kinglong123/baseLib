package com.kinglong.baseapp.mybaseapp;

import com.kinglong.baseapp.mybaseapp.inject.component.ComponentHolder;
import com.kinglong.baseapp.mybaseapp.inject.component.DaggerAppComponent;
import com.kinglong.baseapp.mybaseapp.inject.modules.DataLayerModule;
import com.squareup.leakcanary.LeakCanary;

import android.app.Application;

/**
 * Created by lanjl on 2016/12/5.
 */

public class BaseAppliction extends Application {


    public final void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        initComponent();
    }

    private void initComponent() {

        ComponentHolder.setAppComponent(
                DaggerAppComponent.builder().dataLayerModule(new DataLayerModule(this)).build()
        );
    }

}

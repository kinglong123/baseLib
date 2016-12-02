package com.baseapp.uiframework.view.base;

import com.baseapp.uiframework.frame.base.util.PageManager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
/**
 * Created by lanjl on 2016/12/2.
 */

public abstract class HermesActivity extends AppCompatActivity{

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        preCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        PageManager.INSTANCE.addActivity(this);
        this.beforeCreate(savedInstanceState);
        onBaseCreate(savedInstanceState);
        afterCreate(savedInstanceState);

    }

//    protected abstract int getLayoutId();

    protected void preCreate(Bundle savedInstanceState) {
    }

    protected void beforeCreate(Bundle savedInstanceState) {
    }


    protected abstract void onBaseCreate(Bundle var1);

    protected abstract void afterCreate(Bundle state);
//    protected abstract void initInjector();
    @Override
    protected void onDestroy() {
        super.onDestroy();
        PageManager.INSTANCE.finishActivity(this);
    }
}

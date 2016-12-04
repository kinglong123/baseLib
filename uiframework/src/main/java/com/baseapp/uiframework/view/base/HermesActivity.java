package com.baseapp.uiframework.view.base;

import com.baseapp.uiframework.frame.base.util.PageManager;
import com.kinglong.data.RestoreUtil;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
        //数据读取
        readData(getIntent().getExtras(), savedInstanceState);

        afterCreate(savedInstanceState);

    }

    public void readData(Bundle transitiveState, Bundle saveState) {
        Bundle data = mergeData(transitiveState, saveState);
        if (!data.isEmpty()) {
            RestoreUtil.loadState(data, this);
        }
    }
    @NonNull
    private Bundle mergeData(Bundle transitiveState, Bundle saveState) {
        Bundle data = new Bundle();
        if (transitiveState != null) {
            data.putAll(transitiveState);
        }
        if (saveState != null) {
            data.putAll(saveState);
        }
        return data;
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
       // PageManager.INSTANCE.finishActivity(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        onSave(outState);
    }

    public void onSave(Bundle outState) {
        RestoreUtil.saveState(outState, this);
    }
}

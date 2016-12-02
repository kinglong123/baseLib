package com.kinglong.baseapp.mybaseapp.view.base;

import com.baseapp.uiframework.view.base.HermesActivity;

import android.os.Bundle;
import android.view.View;

/**
 * Created by lanjl on 2016/12/2.
 */

public abstract class BaseActivity extends HermesActivity {
    protected View mRootView;


    @Override
    protected void onBaseCreate(Bundle var1) {
        setContentView(getLayoutId());
        mRootView = findViewById(android.R.id.content);
    }

    protected abstract int getLayoutId();

}

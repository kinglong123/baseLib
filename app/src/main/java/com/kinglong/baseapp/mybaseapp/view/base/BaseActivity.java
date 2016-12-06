package com.kinglong.baseapp.mybaseapp.view.base;

import com.baseapp.uiframework.view.base.HermesActivity;
import com.umeng.analytics.MobclickAgent;

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
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);//统计分析
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);//统计分析
    }

//    @Override
//    protected <T> Observable<T> bindLifecycle(Observable<T> observable) {
//        return super.bindLifecycle(observable)
//                .subscribeOn(SchedulerFactory.getIoScheduler())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
}

package com.kinglong.baseapp.mybaseapp.view.main;

import com.kinglong.baseapp.mybaseapp.R;
import com.kinglong.baseapp.mybaseapp.view.base.BaseActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lanjl on 2018/6/20.
 */
public class RxLifecycleComponentsActivity extends BaseActivity{

    @Override
    protected int getLayoutId() {
        return R.layout.dbflow_test;
    }

    @Override
    protected void afterCreate(Bundle state) {
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {


                    @Override
                    public void onNext(@NonNull Long aLong) {
                        Log.i("接收数据", String.valueOf(aLong));
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                });
    }
}

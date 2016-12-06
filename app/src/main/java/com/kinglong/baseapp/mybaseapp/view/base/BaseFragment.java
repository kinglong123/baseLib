package com.kinglong.baseapp.mybaseapp.view.base;

import com.baseapp.uiframework.view.base.HermesFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lanjl on 2016/12/2.
 */

public abstract class BaseFragment extends HermesFragment {


    public BaseFragment(){

    }

    protected View mRootView;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        mRootView = inflater.inflate(getLayoutId(), null);
        return mRootView;
    }

    protected abstract int getLayoutId();


    @SuppressWarnings("unchecked")
    protected <T> T findViewCall(int id) {
        if (mRootView != null) {
            return (T) mRootView.findViewById(id);
        }
        return null;
    }
    @Override
    protected <T> Observable<T> bindLifecycle(Observable<T> observable) {
        return super.bindLifecycle(observable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

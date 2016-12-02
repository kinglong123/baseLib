package com.kinglong.baseapp.mybaseapp.view.base;

import com.baseapp.uiframework.view.base.HermesFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

}

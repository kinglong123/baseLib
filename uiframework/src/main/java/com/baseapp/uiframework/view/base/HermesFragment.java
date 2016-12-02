package com.baseapp.uiframework.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lanjl on 2016/12/2.
 */

public abstract class HermesFragment extends Fragment {
    protected LayoutInflater mInflater;
    protected View           mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        mInflater = inflater;
        mRootView = createView(inflater, container, savedInstanceState);
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        afterCreate(savedInstanceState);

    }

    protected abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle state);

    protected abstract void afterCreate(Bundle state);

}

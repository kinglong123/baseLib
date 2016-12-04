package com.baseapp.uiframework.view.base;

import com.kinglong.data.RestoreUtil;

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
        RestoreUtil.loadState(getBundle(savedInstanceState), this);
        afterCreate(savedInstanceState);
    }
    private Bundle getBundle(Bundle savedInstanceState) {
        Bundle extras = savedInstanceState;
        if (extras == null) {
            extras = new Bundle();
            Bundle base = getActivity().getIntent().getExtras();

            if (base != null) {
                extras.putAll(base);
            }
            if (getArguments() != null) {
                extras.putAll(getArguments());
            }
        }
        return extras;
    }

    protected abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle state);

    protected abstract void afterCreate(Bundle state);
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        onSave(outState);
    }

    public void onSave(Bundle outState) {
        RestoreUtil.saveState(outState, this);
    }
}

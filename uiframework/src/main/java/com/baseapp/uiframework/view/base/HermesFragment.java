package com.baseapp.uiframework.view.base;

import com.kinglong.data.RestoreUtil;
import com.trello.rxlifecycle.android.FragmentEvent;
import com.trello.rxlifecycle.components.support.RxFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lanjl on 2016/12/2.
 */

public abstract class HermesFragment extends RxFragment {
    protected LayoutInflater mInflater;
    protected View           mRootView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

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

    @SuppressWarnings("unchecked")
    protected <T> rx.Observable<T> bindLifecycle(rx.Observable<T> observable) {
        rx.Observable.Transformer<? super T, ? extends T> transformer = getTransformer();
        return observable.compose(transformer);
    }

    public <T> rx.Observable.Transformer<? super T, ? extends T> getTransformer() {
        return  bindUntilEvent(FragmentEvent.STOP);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventMainThread(String messageEvent) {

        System.out.println("在主线程中收到2222"+Thread.currentThread().getName());
    }
}

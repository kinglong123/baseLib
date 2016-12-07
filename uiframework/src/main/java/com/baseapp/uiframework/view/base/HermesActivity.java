package com.baseapp.uiframework.view.base;

import com.baseapp.uiframework.frame.base.util.PageManager;
import com.kinglong.data.RestoreUtil;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import android.os.Bundle;
import android.support.annotation.NonNull;

import rx.Observable;

/**
 * Created by lanjl on 2016/12/2.
 */

public abstract class HermesActivity extends RxAppCompatActivity {

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        preCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        onSave(outState);
    }

    public void onSave(Bundle outState) {
        RestoreUtil.saveState(outState, this);
    }


    @SuppressWarnings("unchecked")
    protected <T> Observable<T> bindLifecycle(Observable<T> observable) {
        Observable.Transformer<? super T, ? extends T> transformer = getTransformer();
        return observable.compose(transformer);
    }

    public <T> Observable.Transformer<? super T, ? extends T> getTransformer() {
        return  bindUntilEvent(ActivityEvent.STOP);

       // return bindToLifecycle();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消事件注册
        EventBus.getDefault().unregister(this);
        // PageManager.INSTANCE.finishActivity(this);
    }

    //在UI线程中执行
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventMainThread(String messageEvent) {

        System.out.println("在主线程中收到111"+Thread.currentThread().getName());
    }
}

package com.kinglong.baseapp.mybaseapp.view.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by lanjl on 2019/4/10.
 */
public class MyService extends Service {
    private boolean isRunning;

    @Override
    public void onCreate() { // 创建时执行
        super.onCreate();
        System.out.println("11111onCreate");
    }
    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("11111onunbind");
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) { // 启动时执行
        super.onStart(intent, startId);
        System.out.println("11111onStart");

        isRunning = true;

        // new Thread() {
        // public void run() {
        // for (int i = 0; isRunning; i++) {
        // System.out.println(Thread.currentThread().getName() + ": " + i);
        // try {
        // Thread.sleep(1000);
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }
        // }
        // }
        // }.start();
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onDestroy() { // 停止时执行

        super.onDestroy();
        System.out.println("11111onDestroy");
        isRunning = false;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new  LocalBinder();
    }

    public class LocalBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }

        public void todoSomeThingneibu(String ss) {
            //todo
            System.out.println("方法写在LocalBinder里面:"+ss);


        }
    }


    public void todoSomeThingwaibu(String ss) {
        //todo
        System.out.println("方法写在LocalBinder外面:"+ss);


    }
}

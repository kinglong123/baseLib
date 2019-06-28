package com.konglong.down.service;

import com.konglong.down.IDownloadAidlService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by lanjl on 2019/4/10.
 */
public class DownloadService  extends Service {



    private final IDownloadAidlService.Stub mStub = new IDownloadAidlService.Stub() {

        @Override
        public void start(long taskId) throws RemoteException {

        }

        @Override
        public void startRightNow(long taskId) throws RemoteException {

        }

        @Override
        public void pause(long taskId) throws RemoteException {

        }
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mStub;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化数据库
        //开启 轮询的线程  mDownloadEmitterManager


    }
}

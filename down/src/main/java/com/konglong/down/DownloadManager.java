package com.konglong.down;

import com.kinglong.commons.util.Ln;
import com.konglong.down.service.DownloadService;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * Created by lanjl on 2019/4/10.
 */
public class DownloadManager implements IDownloadManager {

    public static final String TAG ="DownloadManager";
    private static DownloadManager mDownloadManager;

    private Context      mContext;
    IDownloadAidlService mIDownloadAidlService;

    public static DownloadManager newInstance(Context context) {
        if (mDownloadManager == null) {
            synchronized (DownloadManager.class) {
                if (mDownloadManager == null) {
                    mDownloadManager = new DownloadManager(context);
                }
            }

        }
        return mDownloadManager;
    }

    public static DownloadManager getInstance() {
        if (null == mDownloadManager) {
            throw new RuntimeException("DownloadManager hasn't been initialized! Pls call init() method at first.");
        }
        return mDownloadManager;
    }


    public static void init(Context context) {
        if(mDownloadManager ==null){
            mDownloadManager =newInstance(context);
            //一些参数设置 下载保存路径之类的
        }
    }


    private DownloadManager(Context context) {
        Ln.d(TAG,"DownloadManager");
        mContext = context;

        bindService(context);
    }

    private void bindService(Context context) {

//        Intent intent = new Intent();

        Intent intent = new Intent(context, DownloadService.class);

        context.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);


    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIDownloadAidlService = IDownloadAidlService.Stub.asInterface(service);


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //是否需要重连

        }
    };

    @Override
    public void start(long taskId) {

    }

    @Override
    public void startRightNow(long taskId) {

    }

    @Override
    public void pause(long taskId) {

    }
}

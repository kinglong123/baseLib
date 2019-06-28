package com.konglong.down;

/**
 * Created by lanjl on 2019/4/10.
 */
public interface IDownloadManager {

    public void start(long taskId);

    public void startRightNow(long taskId);

    public void pause(long taskId);



}

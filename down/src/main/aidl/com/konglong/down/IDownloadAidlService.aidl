// IDownloadAidlService.aidl.aidl
package com.konglong.down;

// Declare any non-default types here with import statements

interface IDownloadAidlService {

      void start(long taskId);

      void startRightNow(long taskId);

      void pause(long taskId);
}

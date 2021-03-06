package service.cn.com.rxdownload;

import android.content.Context;

import io.reactivex.Observable;
import service.cn.com.rxdownload.entity.DownloadBean;
import service.cn.com.rxdownload.entity.DownloadStatus;
import service.cn.com.rxdownload.function.DownloadHelper;

/**
 * Created by lanjl on 2018/7/13.
 */
public class RxDownload {

    private volatile static RxDownload instance;
    private Context context;
    private DownloadHelper downloadHelper;

    private RxDownload(Context context) {
        this.context = context.getApplicationContext();

    }

    /**
     * Return RxDownload Instance
     *
     * @param context context
     * @return RxDownload
     */
    public static RxDownload getInstance(Context context) {
        if (instance == null) {
            synchronized (RxDownload.class) {
                if (instance == null) {
                    instance = new RxDownload(context);
                }
            }
        }
        return instance;
    }


    /**
     * Normal download.
     * <p>
     * You can construct a DownloadBean to save extra data to the database.
     *
     * @param downloadBean download bean.
     * @return Observable<DownloadStatus>
     */
    public Observable<DownloadStatus> download(DownloadBean downloadBean) {
        return downloadHelper.downloadDispatcher(downloadBean);
    }


}

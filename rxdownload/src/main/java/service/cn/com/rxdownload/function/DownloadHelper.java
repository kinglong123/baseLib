package service.cn.com.rxdownload.function;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;
import service.cn.com.rxdownload.entity.DownloadBean;
import service.cn.com.rxdownload.entity.DownloadStatus;

/**
 * Created by lanjl on 2018/7/13.
 */
public class DownloadHelper {
    private DownloadApi downloadApi;
    private FileHelper fileHelper;
    public DownloadHelper(Context context) {
        downloadApi = DownApiRetrofitProvider.INSTANCE.getInstance().create(DownloadApi.class);
        this.fileHelper = new FileHelper(3);
    }


    /**
     * dispatch download
     *
     * @param bean download bean
     * @return DownloadStatus
     */
    public Observable<DownloadStatus> downloadDispatcher(final DownloadBean bean) {
        return Observable.just(1)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        //addTempRecord(bean);
                    }
                })
//                .flatMap(new Function<Integer, ObservableSource<DownloadType>>() {
//                    @Override
//                    public ObservableSource<DownloadType> apply(Integer integer) throws Exception {
//                        return getDownloadType(bean.getUrl());
//                    }
//                })
//                .flatMap(new Function<DownloadType, ObservableSource<DownloadStatus>>() {
//                    @Override
//                    public ObservableSource<DownloadStatus> apply(DownloadType type) throws Exception {
//                        return download(type);
//                    }
//                })
                .flatMap(new Function<Integer, ObservableSource<DownloadStatus>>() {
                    @Override
                    public ObservableSource<DownloadStatus> apply(Integer integer) throws Exception {

                        return download(bean);
                    }
                })

                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //logError(throwable);
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        //recordTable.delete(bean.getUrl());
                    }
                });
    }

    private ObservableSource<DownloadStatus> download(final DownloadBean bean)
            throws IOException, ParseException {
//        downloadType.prepareDownload();
        return startDownload(bean);
    }


    public Observable<DownloadStatus> startDownload(final DownloadBean bean) {
        return Flowable.just(1)
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
//                        log(startLog());
//                        record.start();
                    }
                })
                .flatMap(new Function<Integer, Publisher<DownloadStatus>>() {
                    @Override
                    public Publisher<DownloadStatus> apply(Integer integer) throws Exception {
                        return downloadFile(bean);
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<DownloadStatus, DownloadStatus>() {
                    @Override
                    public DownloadStatus apply(@NonNull DownloadStatus status) throws Exception {
                        return status;
                    }
                })
                .toObservable();
    }

    protected Publisher<DownloadStatus> downloadFile(final DownloadBean bean) {
        return downloadApi.download(null, bean.getUrl())
                .flatMap(new Function<Response<ResponseBody>, Publisher<DownloadStatus>>() {
                    @Override
                    public Publisher<DownloadStatus> apply(Response<ResponseBody> response) throws Exception {
                        return save(bean,response);
                    }
                });

    }
    private Publisher<DownloadStatus> save(final DownloadBean bean,final Response<ResponseBody> response) {
        return Flowable.create(new FlowableOnSubscribe<DownloadStatus>() {
            @Override
            public void subscribe(FlowableEmitter<DownloadStatus> e) throws Exception {
//                record.save(e, response);
                fileHelper.saveFile(e, file(), response);
            }
        }, BackpressureStrategy.LATEST);
    }
    public File file() {
        return new File("sasda");
    }

}

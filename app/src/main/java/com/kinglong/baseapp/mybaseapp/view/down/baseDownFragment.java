package com.kinglong.baseapp.mybaseapp.view.down;

import com.kinglong.baseapp.mybaseapp.R;
import com.kinglong.baseapp.mybaseapp.view.base.BaseFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lanjl on 2018/7/13.
 */
public class baseDownFragment extends BaseFragment {

    @InjectView(R.id.textView)
    TextView mTextView;

    @InjectView(R.id.finish)
    Button mFinish;

    @InjectView(R.id.img)
    ImageView mImg;

    @InjectView(R.id.status)
    TextView mStatus;

    @InjectView(R.id.percent)
    TextView mPercent;

    @InjectView(R.id.progress)
    ProgressBar mProgress;

    @InjectView(R.id.size)
    TextView mSize;

    @InjectView(R.id.action)
    Button mAction;

    @InjectView(R.id.content_basic_download)
    RelativeLayout mContentBasicDownload;

    public static baseDownFragment newInstance() {

        Bundle args = new Bundle();

        baseDownFragment fragment = new baseDownFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_base_down;
    }

    @Override
    protected void afterCreate(Bundle state) {
        mAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public final static String URL = "http://dldir1.qq.com/weixin/android/weixin6330android920.apk";

    private void start() {

//        RxDownload.getInstance(getContext()).download(new DownloadBean.Builder(URL)
//                .setSaveName(null).setSavePath(null).build()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new  Observer<DownloadStatus>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
////                        disposable = d;
////                        downloadController.setState(new DownloadController.Started());
//                    }
//
//                    @Override
//                    public void onNext(DownloadStatus status) {
//
//                        System.out.println("111111111111111:"+status.getPercent());
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
////                        downloadController.setState(new DownloadController.Paused());
//                    }
//
//                    @Override
//                    public void onComplete() {
////                        downloadController.setState(new DownloadController.Completed());
//                    }
//                });
//        RxDownload.getInstance
//
//
//
//        RxPermissions.getInstance(this)
//                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                .doOnNext(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean aBoolean) throws Exception {
//                        if (!aBoolean) {
//                            throw new RuntimeException("no permission");
//                        }
//                    }
//                })
//                .observeOn(Schedulers.io())
//                .compose(rxDownload.<Boolean>transform(url))
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new  Observer<DownloadStatus>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        disposable = d;
//                        downloadController.setState(new DownloadController.Started());
//                    }
//
//                    @Override
//                    public void onNext(DownloadStatus status) {
//                        binding.contentBasicDownload.progress.setIndeterminate(status.isChunked);
//                        binding.contentBasicDownload.progress.setMax((int) status.getTotalSize());
//                        binding.contentBasicDownload.progress.setProgress((int) status.getDownloadSize());
//                        baseModel.setPercent(status.getPercent());
//                        System.out.println("111111111111111");
//                        baseModel.setSize(status.getFormatStatusString());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        downloadController.setState(new DownloadController.Paused());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        downloadController.setState(new DownloadController.Completed());
//                    }
//                });
    }




















    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater,  container,savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}

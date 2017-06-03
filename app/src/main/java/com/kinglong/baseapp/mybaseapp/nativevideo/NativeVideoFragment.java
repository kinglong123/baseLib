package com.kinglong.baseapp.mybaseapp.nativevideo;

import com.kinglong.baseapp.mybaseapp.R;
import com.kinglong.baseapp.mybaseapp.view.base.BaseFragment;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

import java.io.File;

import butterknife.InjectView;

/**
 * Created by lanjl on 2017/5/30.
 */

public class NativeVideoFragment extends BaseFragment {


    @InjectView(R.id.et_path)
    EditText mEtPath;

    @InjectView(R.id.seekBar)
    SeekBar mSeekBar;

    @InjectView(R.id.btn_play)
    Button mBtnPlay;

    @InjectView(R.id.btn_pause)
    Button mBtnPause;

    @InjectView(R.id.btn_replay)
    Button mBtnReplay;

    @InjectView(R.id.btn_stop)
    Button mBtnStop;

    @InjectView(R.id.vv_videoview)
    VideoView mVvVideoview;

    private static String playPath = Environment
            .getExternalStorageDirectory().toString() + File.separator + "guanghui.mp4";
    public static NativeVideoFragment newInstance(){
        NativeVideoFragment nativeVideoFragment = new NativeVideoFragment();
        return nativeVideoFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_videoview;
    }

    @Override
    protected void afterCreate(Bundle state) {


        bindView();

    }

    private void bindView(){

        mBtnReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mVvVideoview.setVideoPath(ApkPath);
//                mVvVideoview.setMediaController(new MediaController(getActivity()));
                mVvVideoview.start();
            }
        });
        MediaController mediaController = new MediaController(getActivity());
        mVvVideoview.setVideoPath(playPath);
        mVvVideoview.setMediaController(mediaController);
//        mediaController.show();
//       new Handler().postDelayed(new Runnable() {
//           @Override
//           public void run() {
//               mediaController.show();
//           }
//       },500) ;


//        new Handler().post(new Runnable() {
//            @Override
//            public void run() {
//                mediaController.show();
//            }
//        });


        mediaController.post(new Runnable() {
            @Override
            public void run() {
                mediaController.show();
            }
        });


//        mVvVideoview.post(new Runnable() {
//            @Override
//            public void run() {
//                mediaController.show();
//            }
//        });

//        mediaController.setMediaPlayer(mVvVideoview); 多余的，可以不设置
    }


}

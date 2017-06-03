package com.kinglong.baseapp.mybaseapp.nativevideo;

import com.kinglong.baseapp.mybaseapp.R;
import com.kinglong.baseapp.mybaseapp.view.base.BaseActivity;
import com.kinglong.baseapp.mybaseapp.view.base.BaseFragment;
import com.kinglong.baseapp.mybaseapp.view.base.util.DensityUtil;
import com.kinglong.data.Restore;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.InjectView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by lanjl on 2017/5/30.
 */

public class NativeVideoByContorlFragment extends BaseFragment implements
        BaseActivity.DispatchFragment {

    private final int MSG_HIDE_CONTROLLER = 10;//控制栏的显示与隐藏

    private final int MSG_UPDATE_PLAY_TIME = 11;//更新播放时间

    private static String playPath = Environment
            .getExternalStorageDirectory().toString() + File.separator + "guanghui.mp4";

    private MediaController.PageType mCurrPageType = MediaController.PageType.SHRINK;//当前是横屏还是竖屏

    @InjectView(R.id.video_view)
    SuperVideoView mSuperVideoView;

    @InjectView(R.id.controller)
    MediaController mMediaController;

    @InjectView(R.id.rl_play)
    RelativeLayout mRelativeLayout;


    public static NativeVideoByContorlFragment newInstance() {
        NativeVideoByContorlFragment nativeVideoFragment = new NativeVideoByContorlFragment();
        return nativeVideoFragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.video_main;
    }

    @Override
    protected void afterCreate(Bundle state) {

        bindView();
        initData();
        loadAndPlay(playPath, 0);

    }


    private void initData() {

    }


    private void loadAndPlay(String palyPath, int seekTime) {

        if (TextUtils.isEmpty(palyPath)) {
            Log.e("TAG", "palyPath should not be null");
            return;
        }
        mSuperVideoView.setOnPreparedListener(mOnPreparedListener);

        mSuperVideoView.setVideoPath(palyPath);

        mSuperVideoView.setVisibility(VISIBLE);
        startPlayVideo(seekTime);
    }

    private Timer mUpdateTimer;

    /**
     * 播放视频
     * should called after setVideoPath()
     */
    private void startPlayVideo(int seekTime) {
        if (null == mUpdateTimer) {
            resetUpdateTimer();   //开始更新时间
        }
        resetHideTimer();
        mSuperVideoView.setOnCompletionListener(mOnCompletionListener);
        mSuperVideoView.start();
        if (seekTime > 0) {
            mSuperVideoView.seekTo(seekTime);
        }
        mMediaController.setPlayState(MediaController.PlayState.PLAY);
    }

    private MediaPlayer.OnCompletionListener mOnCompletionListener
            = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
//            stopUpdateTimer();
//            stopHideTimer(true);
//            mMediaController.playFinish(mSuperVideoView.getDuration());
//            mVideoPlayCallback.onPlayFinish();
            Toast.makeText(getActivity(), "视频播放完成", Toast.LENGTH_SHORT).show();
        }
    };

    private MediaPlayer.OnPreparedListener mOnPreparedListener
            = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {
            mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                @Override
                public boolean onInfo(MediaPlayer mp, int what, int extra) {
                    /*
                     * add what == MediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING
                     * fix : return what == 700 in Lenovo low configuration Android System
                     */
                    if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START
                            || what == MediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING) {
                        //mProgressBarView.setVisibility(View.GONE);

                        return true;
                    }
                    return false;
                }
            });

        }
    };

    private void bindView() {
        mMediaController.setMediaControl(mMediaControl);
        mSuperVideoView.setOnTouchListener(mOnTouchVideoListener);
    }

    private View.OnTouchListener mOnTouchVideoListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                showOrHideController();
            }
            return mCurrPageType == MediaController.PageType.EXPAND;
        }
    };

    /***
     *
     */
    private void showOrHideController() {

        if (mMediaController.getVisibility() == VISIBLE) {
            mMediaController.setVisibility(GONE);
        } else {
            mMediaController.setVisibility(VISIBLE);

        }
    }


    private MediaController.MediaControlImpl mMediaControl
            = new MediaController.MediaControlImpl() {


        @Override
        public void onPlayTurn() {
            if (mSuperVideoView.isPlaying()) {
                pausePlay(true);
            } else {
                goOnPlay();
            }
        }

        @Override
        public void onPageTurn() {
            onSwitchPageType();
        }

        @Override
        public void onProgressTurn(MediaController.ProgressState state, int progress) {

            if(state != null) {
                if (state == MediaController.ProgressState.START) {
                    mHandler.removeMessages(MSG_HIDE_CONTROLLER);
                } else if (state == MediaController.ProgressState.STOP) {
                    resetHideTimer();
                } else {
                    int time = progress * mSuperVideoView.getDuration() / 100;
                    mSuperVideoView.seekTo(time);
                    updatePlayTime();
                }
            }
        }

        @Override
        public void onSelectSrc(int position) {

        }

        @Override
        public void onSelectFormat(int position) {

        }

        @Override
        public void alwaysShowController() {

        }

        @Override
        public void closePlay() {
            closePlayer();
        }
    };



    /**
     * 暂停播放
     *
     * @param isShowController 是否显示控制条
     */
    public void pausePlay(boolean isShowController) {
        mSuperVideoView.pause();
        mMediaController.setPlayState(MediaController.PlayState.PAUSE);
        stopHideTimer(isShowController);
    }

    /***
     * 继续播放
     */
    public void goOnPlay() {
        mSuperVideoView.start();
        mMediaController.setPlayState(MediaController.PlayState.PLAY);
        resetHideTimer();//继续播放后恢复，控制栏的自动隐藏
        resetUpdateTimer();
    }

    /***
     * 继续播放
     */
    public void goOnPlayByPrpgess(int index) {
        mSuperVideoView.start();
        mMediaController.setPlayState(MediaController.PlayState.PLAY);
        if (index > 0) {
            System.out.println("index111111:"+index);
            mSuperVideoView.seekTo(index);
        }
//        updatePlayTime();//文字
//        updatePlayProgress();//进度条
        resetHideTimer();//继续播放后恢复，控制栏的自动隐藏
        resetUpdateTimer();
    }


    /**
     * 更新播放的进度时间
     */
    private void updatePlayTime() {
        int allTime = mSuperVideoView.getDuration();
        int playTime = mSuperVideoView.getCurrentPosition();

        if (allTime < 0 || playTime == 0) {
            return;
        }
        mMediaController.setPlayProgressTxt(playTime, allTime);
    }


    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == MSG_UPDATE_PLAY_TIME) {
                updatePlayTime();//文字
                updatePlayProgress();//进度条
            } else if (msg.what == MSG_HIDE_CONTROLLER) {
                showOrHideController();
            }
            return false;
        }
    });

    //时间相关：
    private void resetUpdateTimer() {
        stopUpdateTimer();
        mUpdateTimer = new Timer();
        int TIME_UPDATE_PLAY_TIME = 1000;
        mUpdateTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(MSG_UPDATE_PLAY_TIME);
            }
        }, 0, TIME_UPDATE_PLAY_TIME);
    }

    private void stopUpdateTimer() {
        if (mUpdateTimer != null) {
            mUpdateTimer.cancel();
            mUpdateTimer = null;
        }
    }


    private void resetHideTimer() {//开启自动隐藏
        if (!isAutoHideController()) {
            return;
        }
        mHandler.removeMessages(MSG_HIDE_CONTROLLER);
        int TIME_SHOW_CONTROLLER = 4000;
        mHandler.sendEmptyMessageDelayed(MSG_HIDE_CONTROLLER, TIME_SHOW_CONTROLLER);
    }

    /**
     * 更新播放进度条
     */
    private void updatePlayProgress() {
        int allTime = mSuperVideoView.getDuration();
        int playTime = mSuperVideoView.getCurrentPosition();
        if (allTime < 0 || playTime == 0) {
            return;
        }
        int loadProgress = mSuperVideoView.getBufferPercentage();
        int progress = playTime * 100 / allTime;
        mMediaController.setProgressBar(progress, loadProgress);
    }


    //是否自动隐藏控制栏
    private boolean mAutoHideController = true;

    public boolean isAutoHideController() {
        return mAutoHideController;
    }

    public void setAutoHideController(boolean autoHideController) {
        mAutoHideController = autoHideController;
    }

    private void stopHideTimer(boolean isShowController) {//暂停的时候，控制栏一直可见
        mHandler.removeMessages(MSG_HIDE_CONTROLLER);//把这个隐藏消息给删除
//        mMediaController.clearAnimation();
        mMediaController.setVisibility(isShowController ? View.VISIBLE : GONE);
    }


    /**
     * 关闭视频
     */
    public void closePlayer() {
        mMediaController.setPlayState(MediaController.PlayState.PAUSE);
        stopHideTimer(true);
        stopUpdateTimer();
        mSuperVideoView.pause();
        mSuperVideoView.stopPlayback();
        mSuperVideoView.setVisibility(GONE);
        mHandler.removeCallbacksAndMessages(null);
        curIndex = 0;
        isPlaying = false;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (isPlaying) {
            goOnPlayByPrpgess(curIndex);
        }


    }

    @Restore
    boolean isPlaying;

    @Restore
    int curIndex;

    @Override
    public void onPause() {
        super.onPause();
        if (mSuperVideoView.isPlaying()) {
            isPlaying = true;
            pausePlay(true);
        } else {
            isPlaying = false;
        }
        stopUpdateTimer();
        curIndex = mSuperVideoView.getCurrentPosition();
        System.out.println("curIndex111111:"+curIndex);
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        closePlayer();

    }



    /***
     * 旋转屏幕之后回调
     *
     * @param newConfig newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (null == mSuperVideoView) return;
        /***
         * 根据屏幕方向重新设置播放器的大小
         */
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getActivity().getWindow().getDecorView().invalidate();
            float height = DensityUtil.getWidthInPx(getContext());
            float width = DensityUtil.getHeightInPx(getContext());
            mRelativeLayout.getLayoutParams().height = (int) width;
            mRelativeLayout.getLayoutParams().width = (int) height;
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            final WindowManager.LayoutParams attrs = getActivity().getWindow().getAttributes();
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getActivity().getWindow().setAttributes(attrs);
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            float width = DensityUtil.getWidthInPx(getContext());
            float height = DensityUtil.dip2px(getContext(), 220.f);
            mRelativeLayout.getLayoutParams().height = (int) height;
            mRelativeLayout.getLayoutParams().width = (int) width;
        }
    }
    public void onSwitchPageType() {
        if (getActivity().getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            setPageType(MediaController.PageType.SHRINK);
        } else {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            setPageType(MediaController.PageType.EXPAND);
        }
    }



//C
    @Override
    public boolean handlerBackPressed() {
        if (getActivity().getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            setPageType(MediaController.PageType.SHRINK);
            return true;
        }
        return false;
    }

    public void setPageType(MediaController.PageType pageType) {
        mMediaController.setPageType(pageType);
        mCurrPageType = pageType;
    }
}

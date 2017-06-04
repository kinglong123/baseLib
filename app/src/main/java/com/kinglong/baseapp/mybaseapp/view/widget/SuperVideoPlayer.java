package com.kinglong.baseapp.mybaseapp.view.widget;

import com.kinglong.baseapp.mybaseapp.R;
import com.kinglong.baseapp.mybaseapp.nativevideo.MediaController;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lanjl on 2017/6/4.
 */

public class SuperVideoPlayer extends RelativeLayout {




    private final int MSG_HIDE_CONTROLLER = 10;
    private final int MSG_UPDATE_PLAY_TIME = 11;
    private final int MSG_PLAY_ON_TV_RESULT = 12;
    private final int MSG_EXIT_FORM_TV_RESULT = 13;
    private MediaController.PageType mCurrPageType = MediaController.PageType.SHRINK;//当前是横屏还是竖屏

    private Context mContext;
    private SuperVideoView mSuperVideoView;
    private MediaController mMediaController;
    private Timer mUpdateTimer;


    VideoPlayCallbackImpl mVideoPlayCallback;


    public SuperVideoPlayer(Context context, AttributeSet attrs,
            int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SuperVideoPlayer(Context context, AttributeSet attrs, int defStyleAttr,
            int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    public SuperVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SuperVideoPlayer(Context context) {
        super(context);
        initView(context);
    }


    private void initView(Context context) {
        mContext = context;
        View view =View.inflate(context, R.layout.video_player, this);

        mSuperVideoView = (SuperVideoView) view.findViewById(R.id.video_view1);
        mMediaController = (MediaController)  view.findViewById(R.id.controller);
        mMediaController.setMediaControl(mMediaControl);
        mSuperVideoView.setOnTouchListener(mOnTouchVideoListener);


    }

    public void setVideoPlayCallback(VideoPlayCallbackImpl videoPlayCallback) {
        mVideoPlayCallback = videoPlayCallback;
    }





    public void loadAndPlay(String palyPath, int seekTime) {

        if (TextUtils.isEmpty(palyPath)) {
            Log.e("TAG", "palyPath should not be null");
            return;
        }
        mSuperVideoView.setOnPreparedListener(mOnPreparedListener);

        mSuperVideoView.setVideoPath(palyPath);

        mSuperVideoView.setVisibility(VISIBLE);
        startPlayVideo(seekTime);
    }
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
            stopUpdateTimer();
            stopHideTimer(true);
            mMediaController.playFinish(mSuperVideoView.getDuration());
            mVideoPlayCallback.onPlayFinish();
            Toast.makeText(mContext, "视频播放完成", Toast.LENGTH_SHORT).show();
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
                    System.out.println("what111111:"+what);
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





    private View.OnTouchListener mOnTouchVideoListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                showOrHideController();
            }
            return mCurrPageType == MediaController.PageType.EXPAND;
        }
    };

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
//            onSwitchPageType();

            mVideoPlayCallback.onSwitchPageType();
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
            mVideoPlayCallback.onCloseVideo();
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
        stopUpdateTimer();
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
        if (index > 0) {
//            System.out.println("index111111:"+index);
            mSuperVideoView.seekTo(index);
        }
        mSuperVideoView.start();
        mMediaController.setPlayState(MediaController.PlayState.PLAY);

//        updatePlayTime();//文字
//        updatePlayProgress();//进度条
        resetHideTimer();//继续播放后恢复，控制栏的自动隐藏
        resetUpdateTimer();
    }

    /***
     * 设置进度
     */
    public void setPrpgess(int index) {
        if (index > 0 &&mSuperVideoView !=null) {
            System.out.println("index111111:"+index);
            mSuperVideoView.seekTo(index);
        }
    }


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
            mHandler.removeMessages(MSG_UPDATE_PLAY_TIME);
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

    }
    public void setPageType(MediaController.PageType pageType) {
        mMediaController.setPageType(pageType);
        mCurrPageType = pageType;
    }

    boolean isPlaying =false;
    int curIndex;
    public void onPause() {

        if (mSuperVideoView.isPlaying()) {
            isPlaying = true;
            pausePlay(true);
        } else {
            isPlaying = false;
        }
        stopUpdateTimer();
        mHandler.removeCallbacksAndMessages(null);
        curIndex = mSuperVideoView.getCurrentPosition();
        System.out.println("curIndex111111:"+curIndex);
    }

    public void onResume() {
        if (isPlaying) {
            goOnPlayByPrpgess(curIndex);
        }else{
            setPrpgess(curIndex);
        }

    }
    public void  onDestroy(){
        closePlayer();
    }


    public interface VideoPlayCallbackImpl {
        void onCloseVideo();

        void onSwitchPageType();

        void onPlayFinish();
    }





}

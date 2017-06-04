package com.kinglong.baseapp.mybaseapp.nativevideo;

import com.kinglong.baseapp.mybaseapp.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lanjl on 2017/5/31.
 */

public class MediaController extends FrameLayout implements View.OnClickListener,
        SeekBar.OnSeekBarChangeListener {
    private ImageView mPlayImg;//播放按钮
    private SeekBar mProgressSeekBar;//播放进度条
    private TextView mTimeTxt;//播放时间
    private ImageView mExpandImg;//最大化播放按钮
    private ImageView mShrinkImg;//缩放播放按钮


    private ImageView mVideoCloseView;
    private View mMenuView;
    private View mMenuViewPlaceHolder;
    private MediaControlImpl mMediaControl;



    public MediaController(Context context) {
        super(context);
        initView(context);
    }

    public MediaController(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MediaController(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MediaController(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.video_media_controller, this);
        mPlayImg = (ImageView) findViewById(R.id.pause);
        mProgressSeekBar = (SeekBar) findViewById(R.id.media_controller_progress);
        mTimeTxt = (TextView) findViewById(R.id.time);
        mExpandImg = (ImageView) findViewById(R.id.expand);
        mShrinkImg = (ImageView) findViewById(R.id.shrink);
        mVideoCloseView = (ImageView) findViewById(R.id.video_close_view);


        bindView();

    }

    public void setMediaControl(MediaControlImpl mediaControl) {
        mMediaControl = mediaControl;
    }


    private void bindView(){
        mProgressSeekBar.setOnSeekBarChangeListener(this);
        mPlayImg.setOnClickListener(this);
        mExpandImg.setOnClickListener(this);
        mShrinkImg.setOnClickListener(this);
        mVideoCloseView.setOnClickListener(this);


    }
    public void setProgressBar(int progress, int secondProgress) {
        if (progress < 0) progress = 0;
        if (progress > 100) progress = 100;
        if (secondProgress < 0) secondProgress = 0;
        if (secondProgress > 100) secondProgress = 100;
        mProgressSeekBar.setProgress(progress);
        mProgressSeekBar.setSecondaryProgress(secondProgress);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.pause) {
            mMediaControl.onPlayTurn();
        } else if (view.getId() == R.id.expand) {
            mMediaControl.onPageTurn();
        } else if (view.getId() == R.id.shrink) {
            mMediaControl.onPageTurn();
        }else if (view.getId() == R.id.video_close_view) {
            mMediaControl.closePlay();
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {//如果是用户行为触发的,才作相应操作
            mMediaControl.onProgressTurn(ProgressState.DOING, progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        mMediaControl.onProgressTurn(ProgressState.START, 0);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mMediaControl.onProgressTurn(ProgressState.STOP, 0);
    }


    public void setPlayState(PlayState playState) {
        if(playState !=null) {
            mPlayImg.setImageResource(playState == PlayState.PLAY ? R.drawable.biz_video_pause
                    : R.drawable.biz_video_play);
        }
    }


    public void setPageType(PageType pageType) {
        mExpandImg.setVisibility(pageType.equals(PageType.EXPAND) ? GONE : VISIBLE);
        mShrinkImg.setVisibility(pageType.equals(PageType.SHRINK) ? GONE : VISIBLE);
    }


    public void setPlayProgressTxt(int nowSecond, int allSecond) {
        mTimeTxt.setText(getPlayTime(nowSecond, allSecond));
    }
    private String getPlayTime(int playSecond, int allSecond) {
        String playSecondStr = "00:00";
        String allSecondStr = "00:00";
        if (playSecond > 0) {
            playSecondStr = formatPlayTime(playSecond);
        }
        if (allSecond > 0) {
            allSecondStr = formatPlayTime(allSecond);
        }
        return playSecondStr + "/" + allSecondStr;
    }
    @SuppressLint("SimpleDateFormat")
    private String formatPlayTime(long time) {
        DateFormat formatter = new SimpleDateFormat("mm:ss");
        return formatter.format(new Date(time));
    }


    public void playFinish(int allTime) {
        mProgressSeekBar.setProgress(0);
        setPlayProgressTxt(0, allTime);
        setPlayState(PlayState.PAUSE);
    }




    /**
     * 播放状态 播放 暂停
     */
    public enum PlayState {
        PLAY, PAUSE
    }

    /**
     * 播放样式 展开、缩放
     */
    public enum PageType {
        EXPAND, SHRINK
    }



    public enum ProgressState {
        START, DOING, STOP
    }




    public interface MediaControlImpl {
        void onPlayTurn();

        void onPageTurn();

        void onProgressTurn(ProgressState state, int progress);

        void onSelectSrc(int position);

        void onSelectFormat(int position);

        void alwaysShowController();

        void closePlay();
    }


}

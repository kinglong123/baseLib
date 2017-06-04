package com.kinglong.baseapp.mybaseapp.nativevideo;

import com.kinglong.baseapp.mybaseapp.R;
import com.kinglong.baseapp.mybaseapp.view.base.BaseActivity;
import com.kinglong.baseapp.mybaseapp.view.base.BaseFragment;
import com.kinglong.baseapp.mybaseapp.view.base.util.DensityUtil;
import com.kinglong.baseapp.mybaseapp.view.widget.SuperVideoPlayer;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.view.WindowManager;

import java.io.File;

import butterknife.InjectView;

/**
 * Created by lanjl on 2017/5/30.
 */

public class NativeVideoByWidgetFragment extends BaseFragment implements
        BaseActivity.DispatchFragment,SuperVideoPlayer.VideoPlayCallbackImpl {

    @InjectView(R.id.video_view)
    SuperVideoPlayer mSuperVideoView;

    private static String playPath = Environment
            .getExternalStorageDirectory().toString() + File.separator + "guanghui.mp4";


    public static NativeVideoByWidgetFragment newInstance() {
        NativeVideoByWidgetFragment nativeVideoByWidgetFragment = new NativeVideoByWidgetFragment();

        return nativeVideoByWidgetFragment;
    }

    @Override
    protected void afterCreate(Bundle state) {
        mSuperVideoView.setVideoPlayCallback(this);
        mSuperVideoView.loadAndPlay(playPath, 0);
    }

    @Override
    public boolean handlerBackPressed() {
        if (getActivity().getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            mSuperVideoView.setPageType(MediaController.PageType.SHRINK);
            return true;
        }
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.video_player_by_widget;
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
            mSuperVideoView.getLayoutParams().height = (int) width;
            mSuperVideoView.getLayoutParams().width = (int) height;
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            final WindowManager.LayoutParams attrs = getActivity().getWindow().getAttributes();
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getActivity().getWindow().setAttributes(attrs);
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            float width = DensityUtil.getWidthInPx(getContext());
            float height = DensityUtil.dip2px(getContext(), 220.f);
            mSuperVideoView.getLayoutParams().height = (int) height;
            mSuperVideoView.getLayoutParams().width = (int) width;
        }
    }
    


    @Override
    public void onCloseVideo() {
        if(mSuperVideoView != null){
            mSuperVideoView.closePlayer();
        }
        
    }

    @Override
    public void onSwitchPageType() {
        if (getActivity().getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            mSuperVideoView.setPageType(MediaController.PageType.SHRINK);
        } else {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            mSuperVideoView.setPageType(MediaController.PageType.EXPAND);
        }
    }

    @Override
    public void onPlayFinish() {

    }



    @Override
    public void onResume() {
        super.onResume();
        mSuperVideoView.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        mSuperVideoView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSuperVideoView.closePlayer();
    }
}

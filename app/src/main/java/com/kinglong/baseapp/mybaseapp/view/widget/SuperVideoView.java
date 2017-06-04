package com.kinglong.baseapp.mybaseapp.view.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by lanjl on 2017/5/31.
 */

public class SuperVideoView extends VideoView{

    public SuperVideoView(Context context) {
        super(context);
    }

    public SuperVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SuperVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SuperVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}

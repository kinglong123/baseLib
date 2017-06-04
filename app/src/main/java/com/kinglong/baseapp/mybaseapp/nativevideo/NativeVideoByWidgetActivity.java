package com.kinglong.baseapp.mybaseapp.nativevideo;

import com.kinglong.baseapp.mybaseapp.view.base.BaseSingleFragmentActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by lanjl on 2017/5/31.
 */

public class NativeVideoByWidgetActivity extends BaseSingleFragmentActivity {
    NativeVideoByWidgetFragment mNativeVideoByWidgetFragment;

    @Override
    protected Fragment onCreateFragment() {
        mNativeVideoByWidgetFragment = NativeVideoByWidgetFragment.newInstance();
        return mNativeVideoByWidgetFragment;
    }

    @Override
    protected void afterCreate(Bundle state) {

    }


    @Override
    public void onBackPressed() {
        if (mNativeVideoByWidgetFragment != null) {
            if (mNativeVideoByWidgetFragment.handlerBackPressed()) {
                return;
            }
        }
        super.onBackPressed();
    }


}

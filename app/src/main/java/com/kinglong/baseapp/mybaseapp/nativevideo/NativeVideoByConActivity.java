package com.kinglong.baseapp.mybaseapp.nativevideo;

import com.kinglong.baseapp.mybaseapp.view.base.BaseSingleFragmentActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by lanjl on 2017/5/31.
 */

public class NativeVideoByConActivity  extends BaseSingleFragmentActivity {
    NativeVideoByContorlFragment mNativeVideoByContorlFragment;

    @Override
    protected Fragment onCreateFragment() {
        mNativeVideoByContorlFragment = NativeVideoByContorlFragment.newInstance();
        return mNativeVideoByContorlFragment;
    }

    @Override
    protected void afterCreate(Bundle state) {

    }


    @Override
    public void onBackPressed() {
        if (mNativeVideoByContorlFragment != null) {
            if (mNativeVideoByContorlFragment.handlerBackPressed()) {
                return;
            }
        }
        super.onBackPressed();
    }


}

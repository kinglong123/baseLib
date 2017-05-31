package com.kinglong.baseapp.mybaseapp.nativevideo;

import com.kinglong.baseapp.mybaseapp.view.base.BaseSingleFragmentActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by lanjl on 2017/5/30.
 */

public class NativeVideoActivity  extends BaseSingleFragmentActivity {


    @Override
    protected Fragment onCreateFragment() {
        return NativeVideoFragment.newInstance();
    }

    @Override
    protected void afterCreate(Bundle state) {

    }
}

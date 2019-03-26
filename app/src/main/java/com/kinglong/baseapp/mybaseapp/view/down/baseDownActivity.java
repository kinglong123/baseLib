package com.kinglong.baseapp.mybaseapp.view.down;

import com.kinglong.baseapp.mybaseapp.view.base.BaseSingleFragmentActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by lanjl on 2018/7/13.
 */
public class baseDownActivity extends BaseSingleFragmentActivity {

    @Override
    protected Fragment onCreateFragment() {
        return baseDownFragment.newInstance();
    }

    @Override
    protected void afterCreate(Bundle state) {

    }
}

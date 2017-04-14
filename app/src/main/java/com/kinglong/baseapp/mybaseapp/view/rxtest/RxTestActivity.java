package com.kinglong.baseapp.mybaseapp.view.rxtest;

import com.kinglong.baseapp.mybaseapp.view.base.BaseSingleFragmentActivity;
import com.kinglong.baseapp.mybaseapp.view.restore.RestoreTestFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by lanjl on 2017/4/14.
 */

public class RxTestActivity extends BaseSingleFragmentActivity {

    @Override
    protected Fragment onCreateFragment() {
        return RxTestFragment.newInstance();
    }

    @Override
    protected void afterCreate(Bundle state) {

    }

}

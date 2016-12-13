package com.kinglong.baseapp.mybaseapp.view.dbflow;

import com.kinglong.baseapp.mybaseapp.view.base.BaseSingleFragmentActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by lanjl on 2016/12/12.
 */

public class DbflowTestActivity  extends BaseSingleFragmentActivity {

    @Override
    protected Fragment onCreateFragment() {
        return DbflowTestFragment.newInstance();
    }

    @Override
    protected void afterCreate(Bundle state) {

    }
}

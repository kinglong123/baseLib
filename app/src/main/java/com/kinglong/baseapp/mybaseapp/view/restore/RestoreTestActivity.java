package com.kinglong.baseapp.mybaseapp.view.restore;

import com.kinglong.baseapp.mybaseapp.view.base.BaseSingleFragmentActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by lanjl on 2016/12/4.
 */

public class RestoreTestActivity  extends BaseSingleFragmentActivity {

    @Override
    protected Fragment onCreateFragment() {
        return RestoreTestFragment.newInstance();
    }

    @Override
    protected void afterCreate(Bundle state) {

    }
}

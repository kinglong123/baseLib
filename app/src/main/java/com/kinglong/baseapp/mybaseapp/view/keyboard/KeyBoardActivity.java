package com.kinglong.baseapp.mybaseapp.view.keyboard;

import com.kinglong.baseapp.mybaseapp.view.base.BaseSingleFragmentActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by lanjl on 2019/4/4.
 */
public class KeyBoardActivity extends BaseSingleFragmentActivity {

    @Override
    protected Fragment onCreateFragment() {
        return KeyBoardFragment.newInstance();
    }

    @Override
    protected void afterCreate(Bundle state) {

    }
}

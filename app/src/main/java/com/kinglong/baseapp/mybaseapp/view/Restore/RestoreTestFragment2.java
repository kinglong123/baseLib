package com.kinglong.baseapp.mybaseapp.view.Restore;

import com.kinglong.baseapp.mybaseapp.R;
import com.kinglong.baseapp.mybaseapp.view.base.BaseFragment;

import android.os.Bundle;

/**
 * Created by lanjl on 2016/12/4.
 */

public class RestoreTestFragment2 extends BaseFragment {


    public static RestoreTestFragment2 newInstance() {
        
        Bundle args = new Bundle();
        
        RestoreTestFragment2 fragment = new RestoreTestFragment2();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    protected int getLayoutId() {
        return R.layout.kongactiviry;
    }

    @Override
    protected void afterCreate(Bundle state) {

    }
}

package com.kinglong.baseapp.mybaseapp.view.base;

import com.kinglong.baseapp.mybaseapp.R;
import com.kinglong.baseapp.mybaseapp.view.base.util.FragmentUtil;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by lanjl on 2016/12/2.
 */

public abstract class BaseSingleFragmentActivity extends BaseActivity {

    protected Fragment mFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.single_fragment_activity;
    }


    @Override
    protected void onBaseCreate(Bundle bundle) {
//        FragmentUtil.generateContainerForActivity(this);
        super.onBaseCreate(bundle);
        onAttachFragment();
    }


//    @SuppressWarnings("unchecked")
//    protected void onAttachFragment1() {
//        FragmentManager fm = getSupportFragmentManager();
//        Fragment fragment = fm.findFragmentByTag(getFragmentTag());
//        FragmentTransaction ft = fm.beginTransaction();
//        if (fragment == null) {
//            mFragment = onCreateFragment();
//        } else {
//            mFragment = fragment;
//        }
//        if (!fragment.isAdded()) {
//            ft.add(R.id.vw_container, fragment,getFragmentTag());
//        }else {
//            ft.show(fragment);
//        }
//        ft.commit();
//    }


    @SuppressWarnings("unchecked")
    protected void onAttachFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(getFragmentTag());
        if (fragment == null) {
            mFragment = onCreateFragment();
        } else {
            mFragment = fragment;
        }
        FragmentUtil.addFragmentToContentView(this, mFragment, getFragmentTag());
    }

    protected abstract Fragment onCreateFragment();

    protected String getFragmentTag() {
        return "SingleFragment";
    }
}

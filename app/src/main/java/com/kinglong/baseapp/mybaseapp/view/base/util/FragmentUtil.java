package com.kinglong.baseapp.mybaseapp.view.base.util;


import com.kinglong.baseapp.mybaseapp.R;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;


/**
 * @author yangz
 * @version 2015/4/3.
 */
public class FragmentUtil {


    @Deprecated
    public static void generateContainerForActivity(Activity activity) {
        FrameLayout fl = new FrameLayout(activity);
        fl.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        fl.setId(R.id.vw_container);
        activity.setContentView(fl);
    }

    public static void addFragmentToContentView(
            FragmentActivity fragmentActivity, Fragment fragment, String tag) {
        FragmentManager fm = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (!fragment.isAdded()) {
            ft.add(R.id.vw_container, fragment, tag);
        } else {
            ft.show(fragment);
        }
        ft.commit();
    }

}

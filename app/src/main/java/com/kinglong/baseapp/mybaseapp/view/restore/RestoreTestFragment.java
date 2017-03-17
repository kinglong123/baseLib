package com.kinglong.baseapp.mybaseapp.view.restore;

import com.kinglong.baseapp.mybaseapp.R;
import com.kinglong.baseapp.mybaseapp.constant.BoundKey;
import com.kinglong.baseapp.mybaseapp.data.model.MessageEvent;
import com.kinglong.baseapp.mybaseapp.data.model.UserInfo;
import com.kinglong.baseapp.mybaseapp.view.base.BaseFragment;
import com.kinglong.data.Restore;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by lanjl on 2016/12/4.
 */

public class RestoreTestFragment extends BaseFragment {

    @Restore(BoundKey.KEY_USER_INFO)
    UserInfo mUserInfo; //这种传过来的，其实是不需要保持的，android会帮你处理

    @Restore(BoundKey.KEY_USER_NAME)
    String  testNmae;//这种页面自己设置的，不保留活动销毁时，需要进行保存，


    public static RestoreTestFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RestoreTestFragment fragment = new RestoreTestFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    protected int getLayoutId() {
        return R.layout.restore_test;
    }
    TextView mTextView;
    TextView mTextViewSetName;
    TextView mTextViewName;
    TextView mTextViewPost;
    @Override
    protected void afterCreate(Bundle state) {
         mTextView = findViewCall(R.id.tv);
         mTextViewSetName= findViewCall(R.id.tv_setname);
        mTextViewName= findViewCall(R.id.tv_name);

        mTextViewPost= findViewCall(R.id.tv_post);


        mTextViewName.setText(testNmae);

////        mUserInfo = new UserInfo();
//
//        mUserInfo = (UserInfo) getActivity().getIntent().getSerializableExtra(BoundKey.KEY_USER_INFO);
//

         mTextView.setText(mUserInfo.getName());

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(getContext(), RestoreTestActivity2.class);
                 getContext().startActivity(intent);

            }
        });

        mTextViewSetName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testNmae = "sadasd";
                mTextViewName.setText(testNmae);

            }
        });

        mTextViewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("postEvent", Thread.currentThread().getName());
                String message = "";
                if(TextUtils.isEmpty(message)) {
                    message = "defaule message";
                }
                EventBus.getDefault().post(new MessageEvent(message));
            }
        });

    }

    //在UI线程中执行
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventMainThread(MessageEvent messageEvent) {

        System.out.println("RestoreTestFragment在主线程中收到"+Thread.currentThread().getName());
    }
}

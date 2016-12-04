package com.kinglong.baseapp.mybaseapp.view.Restore;

import com.kinglong.baseapp.constant.BoundKey;
import com.kinglong.baseapp.mybaseapp.R;
import com.kinglong.baseapp.mybaseapp.data.model.UserInfo;
import com.kinglong.baseapp.mybaseapp.view.base.BaseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by lanjl on 2016/12/4.
 */

public class RestoreTestActivity3 extends BaseActivity{


    UserInfo mUserInfo;
    TextView mTextView;
    @Override
    protected int getLayoutId() {
        return R.layout.restore_test;
    }

    @Override
    protected void afterCreate(Bundle state) {
        mTextView = (TextView) findViewById(R.id.tv);


//        mUserInfo = new UserInfo();

        mUserInfo = (UserInfo) getIntent().getSerializableExtra(BoundKey.KEY_USER_INFO);


        mTextView.setText(mUserInfo.getName());

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestoreTestActivity3.this, RestoreTestActivity2.class);
                RestoreTestActivity3.this.startActivity(intent);

            }
        });
    }
}

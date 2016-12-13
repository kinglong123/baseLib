package com.kinglong.baseapp.mybaseapp.view.dbflow;

import com.kinglong.baseapp.mybaseapp.R;
import com.kinglong.baseapp.mybaseapp.data.model.BaseEntryDb;
import com.kinglong.baseapp.mybaseapp.data.model.ProjectInfoV2;
import com.kinglong.baseapp.mybaseapp.store.GetProjectInfoStore;
import com.kinglong.baseapp.mybaseapp.view.base.BaseFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import rx.functions.Action1;

/**
 * Created by lanjl on 2016/12/4.
 */

public class DbflowTestFragment extends BaseFragment {

    Button mButton;
    public static DbflowTestFragment newInstance() {
        Bundle args = new Bundle();
        DbflowTestFragment fragment = new DbflowTestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    
    @Override
    protected int getLayoutId() {
        return R.layout.dbflow_test;
    }


    @Override
    protected void afterCreate(Bundle state) {
        mButton = findViewCall(R.id.bt);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindLifecycle(GetProjectInfoStore.get().getProjectInfo()).subscribe(
                        new Action1<BaseEntryDb<ProjectInfoV2>>() {
                            @Override
                            public void call(BaseEntryDb<ProjectInfoV2> projectInfoV2BaseEntryDb) {
                                ProjectInfoV2  projectInfoV2 =  projectInfoV2BaseEntryDb.getData();

                                Toast.makeText(getContext(),projectInfoV2.getTitle(),Toast.LENGTH_LONG).show();

                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {

                            }
                        });
            }
        });

    }
}

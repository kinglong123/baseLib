package com.kinglong.baseapp.mybaseapp.view.dbflow;

import com.kinglong.baseapp.mybaseapp.R;
import com.kinglong.baseapp.mybaseapp.data.model.BaseEntryDb;
import com.kinglong.baseapp.mybaseapp.data.model.Message;
import com.kinglong.baseapp.mybaseapp.data.model.MessageInfo;
import com.kinglong.baseapp.mybaseapp.data.model.Message_Table;
import com.kinglong.baseapp.mybaseapp.data.model.ProjectInfoV2;
import com.kinglong.baseapp.mybaseapp.data.model.ProjectInfoV2_Table;
import com.kinglong.baseapp.mybaseapp.data.util.DbBaseModelDao;
import com.kinglong.baseapp.mybaseapp.store.GetProjectInfoStore;
import com.kinglong.baseapp.mybaseapp.view.base.BaseFragment;
import com.kinglong.commons.util.Ln;
import com.konglong.db.sqlbritehelper.module.init.SqlbriteHelper;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.squareup.sqlbrite.SqlBrite;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by lanjl on 2016/12/4.
 */

public class DbflowTestFragment extends BaseFragment {

    Button mButton;

    TextView mTextView;

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

    Button mButton1;

    @Override
    protected void afterCreate(Bundle state) {
        mButton = findViewCall(R.id.bt);
        mTextView = findViewCall(R.id.tv);

        mButton1 = findViewCall(R.id.btlist);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        GetProjectInfoStore.get().getProjectInfo("1021")
                        .compose(applySchedulers())
                        .subscribe(
                        new Action1<BaseEntryDb<ProjectInfoV2>>() {
                            @Override
                            public void call(BaseEntryDb<ProjectInfoV2> projectInfoV2BaseEntryDb) {
                                ProjectInfoV2 projectInfoV2 = projectInfoV2BaseEntryDb.getData();

                                Toast.makeText(getContext(), projectInfoV2.getTitle(),
                                        Toast.LENGTH_LONG).show();

                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {//401之类的都会在这里除了，不需要代码会直接到这里
                                if(throwable instanceof HttpException ){
                                    Toast.makeText(getContext(),"接口返回错误",Toast.LENGTH_LONG).show();

                                }else if(throwable instanceof UnknownHostException){

                                    Toast.makeText(getContext(),"网络异常",Toast.LENGTH_LONG).show();
                                }else {

                                    Toast.makeText(getContext(), throwable.getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        initLoader();

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ln.d("MainActivity", "projectInfo is empty");
                GetProjectInfoStore.get().getMessageList("121", 3, 3)
                        .compose(applySchedulers())
                        .subscribe(
                        new Action1<BaseEntryDb<MessageInfo>>() {
                            @Override
                            public void call(BaseEntryDb<MessageInfo> messageInfoBaseEntryDb) {

                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
//                                throwable.ge

                                if(throwable instanceof HttpException ){

                                    Ln.d("ddd:"+((HttpException)throwable).getMessage());
                                    Ln.d("dddcode:"+((HttpException)throwable).code());
                                    Toast.makeText(getContext(),((HttpException)throwable).getMessage(),Toast.LENGTH_LONG).show();

                                }else if(throwable instanceof UnknownHostException){

                                    Toast.makeText(getContext(),"请检查您的网络设置",Toast.LENGTH_LONG).show();
                                }else {

                                    Toast.makeText(getContext(), throwable.getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });


//        mButton1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Ln.d("MainActivity", "projectInfo is empty");
//                bindLifecycle(GetProjectInfoStore.get().getMessageList("121", 3, 3)).subscribe(
//                        new AbsAPICallback("ss","ss","ss") {
//                            @Override
//                            protected void onError(ApiException ex) {
//
//                            }
//
//                            @Override
//                            protected void onPermissionError(ApiException ex) {
//
//                            }
//
//                            @Override
//                            protected void onResultError(ApiException ex) {
//
//                            }
//
//                            @Override
//                            public void onNext(Object o) {
//
//                            }
//                        });
//            }
//        });

        initLoader2();

    }

    private void initLoader2() {

        String sql = new Select().from(Message.class)
                .where(Message_Table.uid.eq("121")).getQuery();

        bindLifecycle(SqlbriteHelper.getBriteDatabase().createQuery(Message.NAME, sql)).map(
                new Func1<SqlBrite.Query, List<Message>>() {
                    @Override
                    public List<Message> call(SqlBrite.Query query) {

                        List<Message> lists = new ArrayList<Message>();
                        if (query.run() == null) {
                            return lists;
                        }
                        try {
                            lists = DbBaseModelDao.listFromCursor(query.run(), Message.class);
//                            lists = SqlUtils.convertToList(ProjectInfoV2.class, query.run());
                        } catch (Exception e) {
//                            Ln.e(e);
                        }
                        return lists;

//                        return ;

                    }
                }).subscribe(new Action1<List<Message>>() {
            @Override
            public void call(List<Message> messages) {
                if(messages != null) {
                    System.out.println("1111messages:" + messages.size());
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                System.out.println("throwable111:" + throwable.getMessage());
            }
        });

    }


    Subscription subscription;

    private void initLoader() {
        String sql = new Select().from(ProjectInfoV2.class)
                .where(ProjectInfoV2_Table.UserId.eq("121")).getQuery();

//        subscription =
        bindLifecycle(SqlbriteHelper.getBriteDatabase().createQuery(ProjectInfoV2.NAME, sql))
                .map(new Func1<SqlBrite.Query, List<ProjectInfoV2>>() {
                         @Override
                         public List<ProjectInfoV2> call(SqlBrite.Query query) {

                             List<ProjectInfoV2> lists = new ArrayList<ProjectInfoV2>();
                             if (query.run() == null) {
                                 return lists;
                             }
                             try {
                                 lists = DbBaseModelDao.listFromCursor(query.run(), ProjectInfoV2.class);
//                            lists = SqlUtils.convertToList(ProjectInfoV2.class, query.run());
                             } catch (Exception e) {
//                            Ln.e(e);
                             }
                             return lists;

//                        return ;
                         }

//                     return null;

                     }
                ).subscribe(new Action1<List<ProjectInfoV2>>() {
            @Override
            public void call(List<ProjectInfoV2> projectInfoV2s) {
                if (projectInfoV2s != null) {
                    System.out.println("projectInfoV2s.size():" + projectInfoV2s.size());
//                    Toast.makeText(getContext(), "projectInfoV2s.size()", Toast.LENGTH_LONG).show();
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                System.out.println("throwable:" + throwable.getMessage());
            }
        });

//                .mapToList(ListsItem.MAPPER)
//
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(adapter);

    }

    @Override
    public void onPause() {
//        subscription.unsubscribe();
        super.onPause();
    }
}

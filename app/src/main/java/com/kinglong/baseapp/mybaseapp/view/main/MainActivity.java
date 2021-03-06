package com.kinglong.baseapp.mybaseapp.view.main;

import com.kinglong.baseapp.mybaseapp.R;
import com.kinglong.baseapp.mybaseapp.constant.BoundKey;
import com.kinglong.baseapp.mybaseapp.data.model.BaseEntry;
import com.kinglong.baseapp.mybaseapp.data.model.BaseEntryByJson;
import com.kinglong.baseapp.mybaseapp.data.model.MessageEvent;
import com.kinglong.baseapp.mybaseapp.data.model.UserInfo;
import com.kinglong.baseapp.mybaseapp.nativevideo.NativeVideoActivity;
import com.kinglong.baseapp.mybaseapp.nativevideo.NativeVideoByConActivity;
import com.kinglong.baseapp.mybaseapp.nativevideo.NativeVideoByWidgetActivity;
import com.kinglong.baseapp.mybaseapp.service.biz.UserService;
import com.kinglong.baseapp.mybaseapp.view.base.BaseActivity;
import com.kinglong.baseapp.mybaseapp.view.dbflow.DbflowTestActivity;
import com.kinglong.baseapp.mybaseapp.view.keyboard.KeyBoardActivity;
import com.kinglong.baseapp.mybaseapp.view.restore.RestoreTestActivity;
import com.kinglong.baseapp.mybaseapp.view.restore.RestoreTestActivity3;
import com.kinglong.baseapp.mybaseapp.view.rxtest.RxTestActivity;
import com.kinglong.baseapp.mybaseapp.view.service.ServiceActivirty;
import com.kinglong.baseapp.mybaseapp.view.service.client.ClienActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lanjl on 2016/12/3.
 */

public class MainActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    Button mButton;

    Button mButtonByJson;

    Button mButtonString;

    Button mButtonerr;

    Button mButtonNll;

    Button mButtonArg;

    Button mButtonArg1;

    Button mButtondagger;

    Button mButtonRxjava;

    Button mButtonRxjavalife;

    Button mButtonRxjavalife2;

    Button mButtonDtdbflow;
    Button mButtonRxTest;

    Button mBnavtivevideo;

    Button mBnavtivevideocontorl;

    Button mBnavtivevideowidget;

    Button mLift;
    @Override
    protected void afterCreate(Bundle state) {
        mButton = (Button) findViewById(R.id.bt);
        mButtonByJson = (Button) findViewById(R.id.btjson);
        mButtonString = (Button) findViewById(R.id.btstring);
        mButtonerr = (Button) findViewById(R.id.bterr);
        mButtonArg = (Button) findViewById(R.id.btarg);
        mButtonArg1 = (Button) findViewById(R.id.btarg1);
        mButtondagger = (Button) findViewById(R.id.btdagger);
        mButtonRxjava = (Button) findViewById(R.id.btrxjava);

        mButtonRxjavalife = (Button) findViewById(R.id.btrxjavalife);
        mButtonRxjavalife2 = (Button) findViewById(R.id.btrxjavalife2);

        mButtonDtdbflow= (Button) findViewById(R.id.btdbflow);

        mButtonRxTest= (Button) findViewById(R.id.rxtest);

        mBnavtivevideo= (Button) findViewById(R.id.navtivevideo);
        mBnavtivevideocontorl= (Button) findViewById(R.id.navtivevideocontorl);

        mBnavtivevideowidget = (Button) findViewById(R.id.navtivevideowidget);

        mLift = (Button) findViewById(R.id.lift);

         findViewById(R.id.keyboar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, KeyBoardActivity.class);

                MainActivity.this.startActivity(intent);
            }
        });


        mLift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, RxLifecycleComponentsActivity.class);

                MainActivity.this.startActivity(intent);
            }
        });






















        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<BaseEntry> call = UserService.getProjectInfo();
                call.enqueue(new Callback<BaseEntry>() {
                    @Override
                    public void onResponse(Call<BaseEntry> call, Response<BaseEntry> response) {

                        BaseEntry baseEntry = response.body();
                        System.out.println("baseEntry:" + baseEntry.getMessage());
                        Toast.makeText(getApplication(), baseEntry.getMessage(), Toast.LENGTH_LONG)
                                .show();
                    }

                    @Override
                    public void onFailure(Call<BaseEntry> call, Throwable t) {
                        System.out.println("Throwable:" + t.getMessage());
                    }
                });
            }
        });

        mButtonByJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<BaseEntryByJson> call = UserService.getProjectInfoBysjon();
                call.enqueue(new Callback<BaseEntryByJson>() {
                    @Override
                    public void onResponse(Call<BaseEntryByJson> call,
                            Response<BaseEntryByJson> response) {

                        BaseEntryByJson baseEntry = response.body();
                        System.out.println("baseEntry:" + baseEntry.getMessage());
                        Toast.makeText(getApplication(), baseEntry.getMessage(), Toast.LENGTH_LONG)
                                .show();
                    }

                    @Override
                    public void onFailure(Call<BaseEntryByJson> call, Throwable t) {
                        System.out.println("Throwable:" + t.getMessage());
                    }
                });

            }
        });

        mButtonString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<String> call = UserService.getString();
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        String baseEntry = response.body();
                        System.out.println("baseEntry:" + baseEntry);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println("Throwable:" + t.getMessage());
                    }
                });
            }
        });

        mButtonerr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mButtonNll.getText();

                "123ffffss".substring(15);
//                break;
            }
        });

        mButtonArg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RestoreTestActivity.class);
                Bundle bundle = new Bundle();
                UserInfo userInfo = new UserInfo();
                userInfo.setAge(20);
                userInfo.setName("king");

                bundle.putSerializable(BoundKey.KEY_USER_INFO, userInfo);
                intent.putExtras(bundle);
                MainActivity.this.startActivity(intent);
            }
        });

        mButtonArg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RestoreTestActivity.class);
                Bundle bundle = new Bundle();
                UserInfo userInfo = new UserInfo();
                userInfo.setAge(20);
                userInfo.setName("king");

                bundle.putSerializable(BoundKey.KEY_USER_INFO, userInfo);
                intent.putExtras(bundle);
                MainActivity.this.startActivity(intent);
            }
        });

        mButtonArg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RestoreTestActivity3.class);
                Bundle bundle = new Bundle();
                UserInfo userInfo = new UserInfo();
                userInfo.setAge(20);
                userInfo.setName("king");

                bundle.putSerializable(BoundKey.KEY_USER_INFO, userInfo);
                intent.putExtras(bundle);
                MainActivity.this.startActivity(intent);
            }
        });

        mButtondagger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<BaseEntry> call = UserService.getProjectInfoByDrgger();
                call.enqueue(new Callback<BaseEntry>() {
                    @Override
                    public void onResponse(Call<BaseEntry> call, Response<BaseEntry> response) {

                        BaseEntry baseEntry = response.body();
                        System.out.println("baseEntryDreger:" + baseEntry.getMessage());
                        Toast.makeText(getApplication(), baseEntry.getMessage(), Toast.LENGTH_LONG)
                                .show();
                    }

                    @Override
                    public void onFailure(Call<BaseEntry> call, Throwable t) {
                        System.out.println("ThrowableDreger:" + t.getMessage());
                    }
                });
            }
        });

        mButtonRxjava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable<BaseEntry> observable = UserService.getStringRxApiByDrgger();

                observable
                        .doOnNext(new Action1<BaseEntry>() {
                            @Override
                            public void call(BaseEntry baseEntry) {
                                System.out.println("map1111111111111111doOnNext"+Thread.currentThread().getName());

                            }
                        })

//                        .observeOn(Schedulers.io())
                        .flatMap(new Func1<BaseEntry, Observable<BaseEntry>>() {
                    @Override
                    public Observable<BaseEntry> call(BaseEntry baseEntry) {

                        System.out.println("map1111111111111111flatMap"+Thread.currentThread().getName());
                        return UserService.getStringRxApiByDrgger2();
                    }
                })
                        .subscribeOn(Schedulers.io())//上面的 全部在异步线程中，subscribeOn放在的位置很重要，影响前面的doonnex和map的线程
                        .observeOn(AndroidSchedulers.mainThread())

                         .map(new Func1<BaseEntry, BaseEntry>() {


                             @Override
                             public BaseEntry call(BaseEntry baseEntry) {
                                 System.out.println("map1111111111111111333333"+Thread.currentThread().getName());

                                 return baseEntry;
                             }
                         })
                        .subscribe(new Action1<BaseEntry>() {
                            @Override
                            public void call(BaseEntry baseEntry) {
                                System.out.println("map11111111111111114444"+Thread.currentThread().getName());
                                System.out.println("mButtonRxjava:" + baseEntry.getMessage());
                                Toast.makeText(getApplication(), baseEntry.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                throwable.printStackTrace();
                                System.out.println("mButtonRxjava:" + throwable.getMessage());
                                Toast.makeText(getApplication(), throwable.getMessage(),
                                        Toast.LENGTH_LONG).show();

                            }
                        });


            }
        });

        mButtonRxjavalife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, RestoreTestActivity.class);
                Bundle bundle = new Bundle();
                UserInfo userInfo = new UserInfo();
                userInfo.setAge(20);
                userInfo.setName("king");

                bundle.putSerializable(BoundKey.KEY_USER_INFO, userInfo);
                intent.putExtras(bundle);
                MainActivity.this.startActivity(intent);

                Observable<BaseEntry> observable = UserService.getStringRxApiByDrgger();

                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<BaseEntry>() {
                            @Override
                            public void call(BaseEntry baseEntry) {

                                System.out
                                        .println("mButtonRxjavalife:" + baseEntry.getMessage());
                                Toast.makeText(MainActivity.this, baseEntry.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                System.out
                                        .println("mButtonRxjavalife:" + throwable.getMessage());
                                Toast.makeText(MainActivity.this, throwable.getMessage(),
                                        Toast.LENGTH_LONG).show();

                            }
                        });

            }
        });

        //随便测试了eventbus
        mButtonRxjavalife2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, RestoreTestActivity.class);
                Bundle bundle = new Bundle();
                UserInfo userInfo = new UserInfo();
                userInfo.setAge(20);
                userInfo.setName("king");

                bundle.putSerializable(BoundKey.KEY_USER_INFO, userInfo);
                intent.putExtras(bundle);
                MainActivity.this.startActivity(intent);


                Subscription s=  bindLifecycle(UserService.getStringRxApiByDrgger()).doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {

                        System.out
                                .println("1111111mButtonRxjavalife2:doOnUnsubscribe" );
                    }
                })
                        .subscribe(new Action1<BaseEntry>() {
                            @Override
                            public void call(BaseEntry baseEntry) {

                                System.out
                                        .println("11111111mButtonRxjavalife2:" + baseEntry.getMessage());
                                Toast.makeText(MainActivity.this, baseEntry.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                System.out
                                        .println("1111111mButtonRxjavalife:" + throwable.getMessage());
                                Toast.makeText(MainActivity.this, throwable.getMessage(),
                                        Toast.LENGTH_LONG).show();

                            }
                        });

//                s.unsubscribe();
            }
        });

        mButtonDtdbflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DbflowTestActivity.class);

                MainActivity.this.startActivity(intent);
            }
        });

        mButtonRxTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RxTestActivity.class);

                MainActivity.this.startActivity(intent);
            }
        });


        mBnavtivevideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NativeVideoActivity.class);

                MainActivity.this.startActivity(intent);
            }
        });


        mBnavtivevideocontorl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NativeVideoByConActivity.class);

                MainActivity.this.startActivity(intent);
            }
        });


        mBnavtivevideowidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NativeVideoByWidgetActivity.class);

                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ServiceActivirty.class);

                MainActivity.this.startActivity(intent);
            }
        });
        findViewById(R.id.service_remote).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ClienActivity.class);

                MainActivity.this.startActivity(intent);
            }
        });


    }
    //在UI线程中执行
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventMainThread(MessageEvent messageEvent) {

        System.out.println("MainActivity在主线程中收到"+Thread.currentThread().getName());
    }

}

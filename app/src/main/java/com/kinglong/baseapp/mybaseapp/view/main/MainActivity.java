package com.kinglong.baseapp.mybaseapp.view.main;

import com.kinglong.baseapp.mybaseapp.R;
import com.kinglong.baseapp.mybaseapp.constant.BoundKey;
import com.kinglong.baseapp.mybaseapp.data.model.BaseEntry;
import com.kinglong.baseapp.mybaseapp.data.model.BaseEntryByJson;
import com.kinglong.baseapp.mybaseapp.data.model.UserInfo;
import com.kinglong.baseapp.mybaseapp.service.biz.UserService;
import com.kinglong.baseapp.mybaseapp.view.Restore.RestoreTestActivity;
import com.kinglong.baseapp.mybaseapp.view.Restore.RestoreTestActivity3;
import com.kinglong.baseapp.mybaseapp.view.base.BaseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
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

                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<BaseEntry>() {
                            @Override
                            public void call(BaseEntry baseEntry) {

                                System.out.println("mButtonRxjava:" + baseEntry.getMessage());
                                Toast.makeText(getApplication(), baseEntry.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
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

//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
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
//                    }
//                }, 1000);

            }
        });


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

//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {


                bindLifecycle(UserService.getStringRxApiByDrgger()).doOnUnsubscribe(new Action0() {
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
//                    }
//                }, 1000);

            }
        });

    }

}

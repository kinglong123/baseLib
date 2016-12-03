package com.kinglong.baseapp.mybaseapp.view.main;

import com.kinglong.baseapp.mybaseapp.R;
import com.kinglong.baseapp.mybaseapp.data.model.BaseEntry;
import com.kinglong.baseapp.mybaseapp.data.model.BaseEntryByJson;
import com.kinglong.baseapp.mybaseapp.service.biz.UserService;
import com.kinglong.baseapp.mybaseapp.view.base.BaseActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    @Override
    protected void afterCreate(Bundle state) {
        mButton =   (Button)findViewById(R.id.bt);


        mButtonByJson=   (Button)findViewById(R.id.btjson);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<BaseEntry> call = UserService.getProjectInfo();
                call.enqueue(new Callback<BaseEntry>() {
                    @Override
                    public void onResponse(Call<BaseEntry> call, Response<BaseEntry> response) {

                        BaseEntry baseEntry = response.body();
                        System.out.println("baseEntry:"+baseEntry.getMessage());
                    }

                    @Override
                    public void onFailure(Call<BaseEntry> call, Throwable t) {
                        System.out.println("Throwable:"+t.getMessage());
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
                    public void onResponse(Call<BaseEntryByJson> call, Response<BaseEntryByJson> response) {

                        BaseEntryByJson baseEntry = response.body();
                        System.out.println("baseEntry:"+baseEntry.getMessage());
                        Toast.makeText(getApplication(),baseEntry.getMessage(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<BaseEntryByJson> call, Throwable t) {
                        System.out.println("Throwable:"+t.getMessage());
                    }
                });

            }
        });
    }

}

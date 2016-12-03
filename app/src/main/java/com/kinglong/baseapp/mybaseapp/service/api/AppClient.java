package com.kinglong.baseapp.mybaseapp.service.api;

import com.kinglong.baseapp.mybaseapp.data.util.StringConverterFactory;
import com.kinglong.data.ObjectMapperUtils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by lanjl on 2016/12/3.
 */

public enum AppClient {

    INSTANCE;

    private static InterAppClientApi api;
    private static InterAppClientApi apiByJson;
    protected static String URL="http://dyapi.91open.com";
    //    protected static String Url="http://dyapi.91open.com/v1/1021/app/getnewprojectinfo";
    static {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(URL)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();
        api = mRetrofit.create(InterAppClientApi.class);
    }




    static {
        Retrofit mRetrofitJson = new Retrofit.Builder()
                .baseUrl(URL)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(
                        ObjectMapperUtils.getMapperInstance()))//Unrecognized field 自己定义的ObjectMapper
                .client(new OkHttpClient())
                .build();
        apiByJson = mRetrofitJson.create(InterAppClientApi.class);
    }



    public InterAppClientApi getApi() {
        return api;
    }

    public InterAppClientApi getApiByJson() {
        return apiByJson;
    }
}

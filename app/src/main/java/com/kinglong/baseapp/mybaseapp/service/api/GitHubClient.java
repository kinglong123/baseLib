package com.kinglong.baseapp.mybaseapp.service.api;

import com.kinglong.baseapp.mybaseapp.data.util.StringConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lanjl on 2016/12/3.
 */
//github api 测试
public enum GitHubClient {

    INSTANCE;

    private static InterGitHubClientApi api;

    protected static String URL="https://api.github.com/repos/kinglong123/baseLib/contents/";
    //    protected static String Url="http://dyapi.91open.com/v1/1021/app/getnewprojectinfo";


    //https://api.github.com/repos/kinglong123/baseLib/contents/json/string.json




    static {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(URL)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();
        api = mRetrofit.create(InterGitHubClientApi.class);
    }

    public InterGitHubClientApi getApi() {
        return api;
    }


}

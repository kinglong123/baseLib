package com.kinglong.baseapp.mybaseapp.inject.modules;

//import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import com.kinglong.baseapp.mybaseapp.data.util.StringConverterFactory;
import com.kinglong.baseapp.mybaseapp.service.api.InterAppClientApi;
import com.kinglong.baseapp.mybaseapp.service.api.InterAppRxClientApi;

import android.content.Context;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by lanjl on 2016/12/5.
 */
@Module
public class DataLayerModule {

    private Context mContext;
//    http://test.api.yddx.huayu.nd

//    protected static String URL = "http://dyapi.91open.com/";

    protected static String URL = " http://test.api.yddx.huayu.nd";
    public DataLayerModule(Context context) {
        mContext = context.getApplicationContext();
    }

    @Provides
    @Singleton
    public InterAppClientApi provideServiceClientApi(Context context,
            OkHttpClient client) {

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(URL)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return mRetrofit.create(InterAppClientApi.class);

    }

    @Provides
    @Singleton
    public InterAppRxClientApi provideServiceRxClientApi(Context context,
            OkHttpClient client) {

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return mRetrofit.create(InterAppRxClientApi.class);

    }


    @Provides
    @Singleton
    public Context provideGlobalContext() {
        return mContext;
    }


    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {

        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor).addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        HttpUrl httpUrl = request.url().newBuilder()
                                .addQueryParameter("accessToken", "a0772e4e3e594ef382611fc0fcea9ba5")
                                .build();

                        request = request.newBuilder().header("User-Agent", "kinglong").url(httpUrl)
                                .build();

                        return chain.proceed(request);
                    }
                })
                .readTimeout(5, TimeUnit.MINUTES)
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .build();

    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {

        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}

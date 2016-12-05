package com.kinglong.baseapp.mybaseapp.inject.modules;

import com.kinglong.baseapp.mybaseapp.data.util.StringConverterFactory;
import com.kinglong.baseapp.mybaseapp.service.api.InterAppClientApi;

import android.content.Context;

import java.io.IOException;

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
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lanjl on 2016/12/5.
 */
@Module
public class DataLayerModule {

    private Context mContext;


    protected static String URL = "http://dyapi.91open.com/";

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
                                .addQueryParameter("token", "tokenValue")
                                .build();

                        request = request.newBuilder().header("User-Agent", "kinglong").url(httpUrl)
                                .build();

                        return chain.proceed(request);
                    }
                }).build();

    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {

        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}

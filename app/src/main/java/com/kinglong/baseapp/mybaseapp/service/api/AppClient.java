package com.kinglong.baseapp.mybaseapp.service.api;

import com.kinglong.baseapp.mybaseapp.data.util.StringConverterFactory;
import com.kinglong.data.ObjectMapperUtils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
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

    private static InterAppClientApi apiString;

    protected static String URL = "http://dyapi.91open.com";


    protected static String URLSTRING = "http://auxo-forcestudy2-gateway.edu.web.sdp.101.com/";
//用来测试string

    //protected static String Url="http://dyapi.91open.com/v1/1021/app/getnewprojectinfo";


    static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

    static {
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor).addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    HttpUrl httpUrl = request.url().newBuilder()
                            .addQueryParameter("token", "tokenValue")
                            .build();

                    request = request.newBuilder().header("User-Agent", "kinglong").url(httpUrl).build();


                    return chain.proceed(request);
                }
            }).build();
    static {

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(URL)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        api = mRetrofit.create(InterAppClientApi.class);
    }


    static {
        Retrofit mRetrofitJson = new Retrofit.Builder()
                .baseUrl(URL)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(
                        ObjectMapperUtils
                                .getMapperInstance()))//Unrecognized field 自己定义的ObjectMapper
                .client(okHttpClient)

                .build();
        apiByJson = mRetrofitJson.create(InterAppClientApi.class);
    }

    //仅用于测试返回String的情况
    static {
        Retrofit mRetrofitString = new Retrofit.Builder()
                .baseUrl(URLSTRING)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

                .client(okHttpClient)
                .build();
        apiString = mRetrofitString.create(InterAppClientApi.class);
    }


    public InterAppClientApi getApi() {
        return api;
    }

    public InterAppClientApi getApiByJson() {
        return apiByJson;
    }

    public InterAppClientApi getApiGetString() {
        return apiString;
    }

}

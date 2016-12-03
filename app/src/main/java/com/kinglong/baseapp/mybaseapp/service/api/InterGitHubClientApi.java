package com.kinglong.baseapp.mybaseapp.service.api;

import com.kinglong.baseapp.mybaseapp.data.model.BaseEntry;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by lanjl on 2016/12/3.
 */

public interface InterGitHubClientApi {


    @POST("/string.json")
    Call<BaseEntry>  getString();

}

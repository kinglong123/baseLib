package com.kinglong.baseapp.mybaseapp.service.api;

import com.kinglong.baseapp.mybaseapp.data.model.BaseEntry;
import com.kinglong.baseapp.mybaseapp.data.model.BaseEntryByJson;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by lanjl on 2016/12/3.
 */

public interface InterAppClientApi {


    @POST("/v1/{projectId}/app/getnewprojectinfo")
    Call<BaseEntry>  getProjectInfo(
            @Path("projectId") String projectId
    );


    @POST("/v1/{projectId}/app/getnewprojectinfo")
    Call<BaseEntryByJson>  getProjectInfoByJson(
            @Path("projectId") String projectId
    );


    @POST("/")
    Call<String> getStingTest();


}

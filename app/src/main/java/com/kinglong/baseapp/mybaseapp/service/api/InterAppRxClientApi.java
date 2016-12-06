package com.kinglong.baseapp.mybaseapp.service.api;

import com.kinglong.baseapp.mybaseapp.data.model.BaseEntry;
import com.kinglong.baseapp.mybaseapp.data.model.BaseEntryByJson;

import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by lanjl on 2016/12/3.
 */

public interface InterAppRxClientApi {


    @POST("/v1/{projectId}/app/getnewprojectinfo")
    Observable<BaseEntry> getProjectInfo(
            @Path("projectId") String projectId
    );


    @POST("/v1/{projectId}/app/getnewprojectinfo")
    Observable<BaseEntryByJson>  getProjectInfoByJson(
            @Path("projectId") String projectId
    );


    @POST("/")
    Observable<String> getStingTest();


}

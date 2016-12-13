package com.kinglong.baseapp.mybaseapp.service.api;

import com.kinglong.baseapp.mybaseapp.data.model.BaseEntry;
import com.kinglong.baseapp.mybaseapp.data.model.BaseEntryByJson;
import com.kinglong.baseapp.mybaseapp.data.model.BaseEntryDb;
import com.kinglong.baseapp.mybaseapp.data.model.ProjectInfoV2;

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


    @POST("/v1/{projectId}/app/getnewprojectinfo")
    Observable<BaseEntryDb<ProjectInfoV2>> getProjectInfoDb(
            @Path("projectId") String projectId
    );

}

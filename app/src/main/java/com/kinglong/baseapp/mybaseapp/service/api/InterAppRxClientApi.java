package com.kinglong.baseapp.mybaseapp.service.api;

import com.kinglong.baseapp.mybaseapp.data.model.BaseEntry;
import com.kinglong.baseapp.mybaseapp.data.model.BaseEntryByJson;
import com.kinglong.baseapp.mybaseapp.data.model.BaseEntryDb;
import com.kinglong.baseapp.mybaseapp.data.model.MessageInfo;
import com.kinglong.baseapp.mybaseapp.data.model.ProjectInfoV2;

import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
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



    ///v1/1021/message/list?pageIndex=0&pageSize=20&accessToken=a0772e4e3e594ef382611fc0fcea9ba5


    String GET_MESSAGE_LIST = "/v1/{projectId}/message/list";



    @POST(GET_MESSAGE_LIST)
    Observable<BaseEntryDb<MessageInfo>> getMessageList(
            @Path("projectId") String pid,
            @Query("pageIndex") int index,
            @Query("pageSize") int size

    );

}

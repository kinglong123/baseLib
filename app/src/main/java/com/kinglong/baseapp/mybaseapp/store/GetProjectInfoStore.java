package com.kinglong.baseapp.mybaseapp.store;

import com.kinglong.baseapp.mybaseapp.data.model.BaseEntryDb;
import com.kinglong.baseapp.mybaseapp.data.model.ProjectInfoV2;
import com.kinglong.baseapp.mybaseapp.data.util.DbBaseModelDao;
import com.kinglong.baseapp.mybaseapp.service.biz.AppClientManager;
import com.raizlabs.android.dbflow.sql.language.ConditionGroup;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by lanjl on 2016/11/14.
 */
public class GetProjectInfoStore extends AppClientManager {

    public static GetProjectInfoStore get() {
        return new GetProjectInfoStore();
    }

    //

    public static Observable<BaseEntryDb<ProjectInfoV2>>  getProjectInfo(){

      return  getRxApi().getProjectInfoDb("1021").doOnNext(
              new Action1<BaseEntryDb<ProjectInfoV2>>() {
                  @Override
                  public void call(BaseEntryDb<ProjectInfoV2> projectInfoV2BaseEntryDb) {

                      DbBaseModelDao<ProjectInfoV2> dao = new DbBaseModelDao<ProjectInfoV2>(
                              ProjectInfoV2.class, ConditionGroup.clause());



                  }
              });
    }

}

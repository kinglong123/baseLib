package com.kinglong.baseapp.mybaseapp.store;

import com.kinglong.baseapp.mybaseapp.data.model.BaseEntryDb;
import com.kinglong.baseapp.mybaseapp.data.model.Message;
import com.kinglong.baseapp.mybaseapp.data.model.MessageInfo;
import com.kinglong.baseapp.mybaseapp.data.model.ProjectInfoV2;
import com.kinglong.baseapp.mybaseapp.data.model.ProjectInfoV2_Table;
import com.kinglong.baseapp.mybaseapp.data.util.DbBaseBrite;
import com.kinglong.baseapp.mybaseapp.service.biz.AppClientManager;
import com.kinglong.baseapp.mybaseapp.view.base.util.EmptyDataException;
import com.konglong.db.sqlbritehelper.module.init.SqlbriteHelper;
import com.raizlabs.android.dbflow.sql.language.ConditionGroup;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

import static com.raizlabs.android.dbflow.sql.language.ConditionGroup.clause;

/**
 * Created by lanjl on 2016/11/14.
 */
public class GetProjectInfoStore extends AppClientManager {

    public static GetProjectInfoStore get() {
        return new GetProjectInfoStore();
    }

    //

    public static Observable<BaseEntryDb<ProjectInfoV2>>  getProjectInfo(String userId){

      return  getRxApi().getProjectInfoDb("1021").doOnNext(
              new Action1<BaseEntryDb<ProjectInfoV2>>() {
                  @Override
                  public void call(BaseEntryDb<ProjectInfoV2> projectInfoV2BaseEntryDb) {
                      if(projectInfoV2BaseEntryDb ==null){
                          throw  new EmptyDataException();
                      }
                      projectInfoV2BaseEntryDb.throwExceptionIfError();


//                      ConditionGroup group =  ConditionGroup.clause().and(ProjectInfoV2_Table.UserId.eq(userId));

//                      DbBaseModelDao<ProjectInfoV2> dao = new DbBaseModelDao<ProjectInfoV2>(
//                              ProjectInfoV2.class, clause());
//
//
//                      ProjectInfoV2 projectInfoV2 = projectInfoV2BaseEntryDb.getData();
//                      projectInfoV2.setUserId(userId);
//                      projectInfoV2.setUserId2("sss");
//                      dao.update(projectInfoV2);

                      ConditionGroup group =  ConditionGroup.clause().and(ProjectInfoV2_Table.UserId.eq(userId));

                      DbBaseBrite<ProjectInfoV2> dao = new DbBaseBrite<ProjectInfoV2>(
                              ProjectInfoV2.class, clause());

                      dao.setBriteDatabase(SqlbriteHelper.getBriteDatabase());
                      ProjectInfoV2 projectInfoV2 = projectInfoV2BaseEntryDb.getData();
                      projectInfoV2.setUserId(userId);
                      projectInfoV2.setUserId2("sss");
                      dao.update(projectInfoV2);


                  }
              });
    }


    public static Observable<BaseEntryDb<MessageInfo>>  getMessageList(String userId,int index,int size){

        return  getRxApi().getMessageList("1021",index,size).doOnNext(
                new Action1<BaseEntryDb<MessageInfo>>() {
                    @Override
                    public void call(BaseEntryDb<MessageInfo> messageInfoBaseEntryDb) {
//                        messageInfoBaseEntryDb = null;
                        if(messageInfoBaseEntryDb ==null){
                            throw  new EmptyDataException();
                        }
                        messageInfoBaseEntryDb.throwExceptionIfError();
                        MessageInfo messageInfo =     messageInfoBaseEntryDb.getData();
                        List<Message>  messageList = messageInfo.getMessageList();
                        if(messageList != null){
                            for(Message message :messageList){
                                message.setUid(userId);
                            }
                        }

//                        ConditionGroup group =  ConditionGroup.clause().and(ProjectInfoV2_Table.UserId.eq(userId));

//                        ConditionGroup group =  ConditionGroup.clause().and(ProjectInfoV2_Table.UserId.eq(userId));
//
                        DbBaseBrite<Message> dao = new DbBaseBrite<Message>(
                                Message.class, clause());

                        dao.setBriteDatabase(SqlbriteHelper.getBriteDatabase());
//                        ProjectInfoV2 projectInfoV2 = projectInfoV2BaseEntryDb.getData();
//                        projectInfoV2.setUserId(userId);
//                        projectInfoV2.setUserId2("sss");
                        dao.updateList(messageList, size, index * size);

                    }
                });
    }

}

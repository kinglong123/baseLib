package com.kinglong.baseapp.mybaseapp.service.biz;

import com.kinglong.baseapp.mybaseapp.data.model.BaseEntry;
import com.kinglong.baseapp.mybaseapp.data.model.BaseEntryByJson;

import retrofit2.Call;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by lanjl on 2016/12/3.
 */

public class UserService extends AppClientManager {


    //项目配置信息,包括是否显示四个全面,是否支持注册,注册类型,模块开启状态等.
    public static Call<BaseEntry> getProjectInfo()  {
           return   getApi().getProjectInfo("61");

    }


    //项目配置信息,包括是否显示四个全面,是否支持注册,注册类型,模块开启状态等.
    public static Call<BaseEntryByJson> getProjectInfoBysjon()  {
        return   getApiByJson().getProjectInfoByJson("66");
    }



    public static Call<String> getString()  {
        return   getStringApiTest().getStingTest();
    }
    public static Call<BaseEntry> getProjectInfoByDrgger()  {
        return  getStringApiByDrgger().getProjectInfo("99");
    }



    public static Observable<BaseEntry> getStringRxApiByDrgger()  {
        return  getRxApi().getProjectInfo("100").doOnNext(new Action1<BaseEntry>() {
            @Override
            public void call(BaseEntry baseEntry) {
                System.out.println("map1111111111111111222222"+Thread.currentThread().getName());
                try {
                    System.out.println("11111doOnNext:getStringRxApiByDrgger");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).map(new Func1<BaseEntry, BaseEntry>() {
            @Override
            public BaseEntry call(BaseEntry baseEntry) {
                System.out.println("map1111111111111111"+Thread.currentThread().getName());
                return baseEntry;
            }
        });
    }

    public static Observable<BaseEntry> getStringRxApiByDrgger2()  {
        return  getRxApi().getProjectInfo("100").doOnNext(new Action1<BaseEntry>() {
            @Override
            public void call(BaseEntry baseEntry) {
                System.out.println("fffffffffff1111111111"+Thread.currentThread().getName());
                try {

                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).map(new Func1<BaseEntry, BaseEntry>() {
            @Override
            public BaseEntry call(BaseEntry baseEntry) {
                System.out.println("fffffffffff1111111111222222222222"+Thread.currentThread().getName());
                return baseEntry;
            }
        });
    }

}

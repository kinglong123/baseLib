package com.kinglong.baseapp.mybaseapp.service.biz;

import com.kinglong.baseapp.mybaseapp.service.api.AppClient;
import com.kinglong.baseapp.mybaseapp.service.api.InterAppClientApi;

/**
 * Created by lanjl on 2016/12/3.
 */

public class AppService {

    protected static InterAppClientApi getApi() {
        return AppClient.INSTANCE.getApi();
    }

    protected static InterAppClientApi getApiByJson() {
        return AppClient.INSTANCE.getApiByJson();
    }

}

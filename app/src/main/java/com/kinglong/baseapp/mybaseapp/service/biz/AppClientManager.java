package com.kinglong.baseapp.mybaseapp.service.biz;

import com.kinglong.baseapp.mybaseapp.service.api.AppClient;
import com.kinglong.baseapp.mybaseapp.service.api.CompomemtServiceManager;
import com.kinglong.baseapp.mybaseapp.service.api.GitHubClient;
import com.kinglong.baseapp.mybaseapp.service.api.InterAppClientApi;
import com.kinglong.baseapp.mybaseapp.service.api.InterGitHubClientApi;

/**
 * Created by lanjl on 2016/12/3.
 */

public class AppClientManager {

    protected static InterAppClientApi getApi() {
        return AppClient.INSTANCE.getApi();
    }

    protected static InterAppClientApi getApiByJson() {
        return AppClient.INSTANCE.getApiByJson();
    }
    protected static InterGitHubClientApi getGitHubApi() {
        return GitHubClient.INSTANCE.getApi();
    }

    protected static InterAppClientApi getStringApiTest() {
        return AppClient.INSTANCE.getApiGetString();
    }


    protected static InterAppClientApi getStringApiByDrgger() {
        return new CompomemtServiceManager().getClientApi();
    }

}

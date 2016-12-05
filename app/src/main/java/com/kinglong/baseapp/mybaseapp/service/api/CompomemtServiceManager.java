package com.kinglong.baseapp.mybaseapp.service.api;

import com.kinglong.baseapp.mybaseapp.inject.component.ComponentHolder;

import javax.inject.Inject;

/**
 * Created by lanjl on 2016/12/5.
 */

public class CompomemtServiceManager {
    @Inject
    InterAppClientApi mClientApi;

    public CompomemtServiceManager() {
        ComponentHolder.getAppComponent().inject(this);
    }

    public InterAppClientApi getClientApi() {
        return mClientApi;
    }


}

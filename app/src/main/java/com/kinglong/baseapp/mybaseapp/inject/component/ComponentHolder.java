package com.kinglong.baseapp.mybaseapp.inject.component;

/**
 * Created by lanjl on 2016/12/5.
 */

public class ComponentHolder {
    private static AppComponent sAppComponent;

    public static void setAppComponent(AppComponent appComponent) {
        sAppComponent = appComponent;
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

}

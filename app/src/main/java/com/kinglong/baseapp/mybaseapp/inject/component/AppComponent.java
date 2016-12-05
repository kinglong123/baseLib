package com.kinglong.baseapp.mybaseapp.inject.component;

import com.kinglong.baseapp.mybaseapp.inject.modules.DataLayerModule;
import com.kinglong.baseapp.mybaseapp.service.api.CompomemtServiceManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lanjl on 2016/12/5.
 */

@Singleton
@Component(modules = {
        DataLayerModule.class
})
public interface AppComponent {

    void inject(CompomemtServiceManager manager);

      //改用ComponentHolder比较好理解
//    class Instance {
//        static AppComponent mAppComponent;
//
//        public static void init(@NonNull AppComponent component) {
//            mAppComponent = component;
//        }
//
//        public static AppComponent get() {
//            return mAppComponent;
//        }
//    }
}

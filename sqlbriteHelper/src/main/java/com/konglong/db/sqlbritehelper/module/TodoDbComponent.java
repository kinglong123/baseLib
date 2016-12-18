/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.konglong.db.sqlbritehelper.module;

import com.konglong.db.sqlbritehelper.module.manager.DbFlowManager;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = DbFlowModule.class)
public interface TodoDbComponent {

    void inject(DbFlowManager fragment);


    class Instance {
        private static TodoDbComponent sComponent;

        public static void init(@NonNull TodoDbComponent component) {
            sComponent = component;
        }

        public static TodoDbComponent get() {
//            if (sComponent == null) {
//                sComponent = DaggerProDataComponent.builder().build();
//            }
            return sComponent;
        }
    }
}

/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.hiot_clout.injection.component;


import com.example.hiot_clout.injection.PerActivity;
import com.example.hiot_clout.injection.module.ActivityModule;
import com.example.hiot_clout.test.mvptest.TestMVPActivity;
import com.example.hiot_clout.test.networktest.TestNetworkPackActivity;
import com.example.hiot_clout.ui.login.LoginActivity;
import com.example.hiot_clout.ui.main.MainActivity;
import com.example.hiot_clout.ui.main.SplashActivity;
import com.example.hiot_clout.ui.mine.MineFragment;
import com.example.hiot_clout.ui.register.RegisterActivity;
import com.example.hiot_clout.ui.scan.ScanActivity;

import dagger.Component;

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 * <p>
 * Subtypes of ActivityComponent should be decorated with annotation:
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(TestMVPActivity testMVPActivity);

    void inject(TestNetworkPackActivity activity);

    void inject(LoginActivity activity);

    void inject(SplashActivity activity);

    void inject(MineFragment fragment);

    void inject(RegisterActivity activity);

    void inject(ScanActivity scanActivity);


    @Component.Builder
    interface ActivityComponentBuilder {

        ActivityComponent build();

        ActivityComponentBuilder applicationComponent(ApplicationComponent applicationComponent);

        ActivityComponentBuilder activityModule(ActivityModule activityModule);
    }
}

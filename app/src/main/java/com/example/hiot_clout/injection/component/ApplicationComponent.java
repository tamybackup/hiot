package com.example.hiot_clout.injection.component;

import android.app.Application;
import android.content.Context;

import com.example.hiot_clout.App;
import com.example.hiot_clout.data.DataManager;
import com.example.hiot_clout.data.SharedPreferencesHelper;
import com.example.hiot_clout.injection.ApplicationContext;
import com.example.hiot_clout.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(App application);

    @ApplicationContext
    Context context();

    Application application();

    DataManager dataManager();

    SharedPreferencesHelper sharedPreferencesHelper();

    @Component.Builder
    interface ApplicationModuleBuilder {
        ApplicationComponent build();

        ApplicationModuleBuilder applicationModule(ApplicationModule applicationModule);
    }
}

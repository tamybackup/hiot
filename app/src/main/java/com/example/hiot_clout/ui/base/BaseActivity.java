package com.example.hiot_clout.ui.base;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hiot_clout.App;
import com.example.hiot_clout.injection.component.ActivityComponent;
import com.example.hiot_clout.injection.component.ApplicationComponent;
import com.example.hiot_clout.injection.component.DaggerActivityComponent;
import com.example.hiot_clout.injection.module.ActivityModule;
import com.example.hiot_clout.ui.login.LoginActivity;

/**
 * MVP架构Activity基类
 */
public abstract class BaseActivity<V extends BaseView,P extends BasePresenter<V>> extends AppCompatActivity implements BaseView {

    private P presenter;
    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectIndependies();
        presenter = createPresenter();
        if (presenter!=null) {
            presenter.setView((V) this);
        }
    }

    public abstract P createPresenter();

    public abstract void injectIndependies();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.destroy();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }



    public ActivityComponent getActivityComponent() {
        if (null == mActivityComponent) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(getActivityModule())
                    .applicationComponent(getApplicationComponent())
                    .build();
        }
        return mActivityComponent;
    }

    public ApplicationComponent getApplicationComponent() {

        Application application = getApplication();
        App app = (App) application;
        return app.component();
    }

    /**
     * Get an Activity module for dependency injection.
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    public void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 打开新界面，关闭本界面
     * @param cls
     */
    protected void startActivity(Class<?> cls){
        Intent intent=new Intent(this,cls);
        startActivity(intent);
        finish();
    }

    /**
     * 打开新界面，保留本界面
     * @param cls
     */
    protected void startActivityWithoutFinish(Class<?> cls){
        Intent intent=new Intent(this,cls);
        startActivity(intent);
    }

    @Override
    public void tokenOut() {
        startActivity(LoginActivity.class);
    }
}

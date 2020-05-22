package com.example.hiot_clout.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;

import com.example.hiot_clout.R;
import com.example.hiot_clout.data.SharedPreferencesHelper;
import com.example.hiot_clout.ui.base.BaseActivity;
import com.example.hiot_clout.ui.base.BasePresenter;
import com.example.hiot_clout.ui.login.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity {

    private static final int HANDLER_MSG_OPEN_NEW = 1;

    @Inject
    SharedPreferencesHelper sharedPreferencesHelper;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            //如果已登录，跳转主界面
            Intent intent = null;
            if (!TextUtils.isEmpty(sharedPreferencesHelper.getUserToken())) {
                intent = new Intent(SplashActivity.this, MainActivity.class);
            } else {
                //如果未登录，跳转登录界面
                intent = new Intent(SplashActivity.this, LoginActivity.class);
            }
            startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        hideActionBar();
        setFullScreen();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(HANDLER_MSG_OPEN_NEW);
            }
        }, 3000);
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);
    }

    private void hideActionBar() {
// Hide UI
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    private void setFullScreen() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}

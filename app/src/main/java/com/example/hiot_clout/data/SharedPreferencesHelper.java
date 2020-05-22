package com.example.hiot_clout.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hiot_clout.injection.ApplicationContext;

import javax.inject.Inject;

/**
 * sharepreference 类
 */
public class SharedPreferencesHelper {

    private static final String PREF_FILE_NAME = "userconfig";

    private static final String PREF_KEY_USER_TOKEN = "PREF_KEY_USER_TOKEN";

    private Context context;
    private final SharedPreferences mPref;

    /**
     * 构造函数
     *
     * @param context
     */
    @Inject
    public SharedPreferencesHelper(@ApplicationContext Context context) {
        this.context = context;
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 写入用户token
     *
     * @param value
     */
    public void setUserToken(String value) {
        mPref.edit().putString(PREF_KEY_USER_TOKEN, value).apply();
    }

    /**
     * 读取用户token
     *
     * @return
     */
    public String getUserToken() {
        return mPref.getString(PREF_KEY_USER_TOKEN, "");
    }
}

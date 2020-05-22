package com.example.hiot_clout.utils;

import android.app.Activity;
import android.app.ProgressDialog;

/**
 * 加载提示工具类
 */
public class LoadingUtil {

    private static ProgressDialog progressDialog;

    public static void showLoading(Activity activity, String message) {

        show(activity, message);
    }

    public static void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.hide();
        }
    }

    private static void show(Activity activity, String message) {
        //Activity为空或者已经销毁了
        if (activity == null || activity.isFinishing()) {
            return;
        }

        //已经显示了
        if (progressDialog != null) {
            progressDialog.show();
            return;
        }

        //创建进度对话框
        progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle("提示");
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

}

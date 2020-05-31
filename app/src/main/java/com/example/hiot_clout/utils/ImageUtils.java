package com.example.hiot_clout.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hiot_clout.R;
import com.example.hiot_clout.data.NetworkService;

/**
 *
 * 图片工具类
 */
public class ImageUtils {

    /**
     * 返回图片全路径url
     *
     * @param url
     * @return
     */
    public static String getFullUrl(String url) {
        return NetworkService.BASE_URL + url;
    }

    public static void show(Context context, ImageView imageView, String url){
        Glide.with(context).load(url).apply(getCommonOptions()).into(imageView);
    }

    public static void showCircle(Context context, ImageView imageView, String url) {
        Glide.with(context).load(url).apply(getCommonOptions().circleCrop()).into(imageView);
    }


    /**
     * 获取默认配置
     * @return
     */
    private static RequestOptions getCommonOptions(){
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .centerCrop();
        return options;
    }
}

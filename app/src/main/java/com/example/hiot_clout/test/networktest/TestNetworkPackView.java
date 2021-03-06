package com.example.hiot_clout.test.networktest;

import com.example.hiot_clout.ui.base.BaseView;

/**
 * 网络封装测试MVP架构view层接口
 */
public interface TestNetworkPackView extends BaseView {

    /**
     * 显示token
     * @param token
     */
    void showToken(String token);

    /**
     * 显示信息
     * @param message
     */
    void showMessage(String message);
}

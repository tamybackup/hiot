package com.example.hiot_clout.ui.register;

import com.example.hiot_clout.ui.base.BaseView;

/**
 * 注册模块view层接口
 */
interface RegisterView extends BaseView {

    /**
     * 注册成功后的处理
     *
     * @param userName
     * @param password
     */
    void registerSucc(String userName, String password);

    /**
     * 自动登录处理
     */
    void loginSucc();
}

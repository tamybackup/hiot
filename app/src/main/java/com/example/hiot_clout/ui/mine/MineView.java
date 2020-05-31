package com.example.hiot_clout.ui.mine;

import com.example.hiot_clout.test.networktest.UserBean;
import com.example.hiot_clout.ui.base.BaseView;

/**
 * 用户中心view层接口
 */
public interface MineView extends BaseView {
    /**
     * 刷新用户信息
     *
     * @param userBean
     */
    void refreshUserInfo(UserBean userBean);

    void refreshUserHead(String url);

    /**
     * 重新登录
     */
    void tokenOut();
}

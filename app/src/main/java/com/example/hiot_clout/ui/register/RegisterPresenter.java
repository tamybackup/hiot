package com.example.hiot_clout.ui.register;

import android.text.TextUtils;

import com.example.hiot_clout.data.DataManager;
import com.example.hiot_clout.test.networktest.LoginResultDTO;
import com.example.hiot_clout.test.networktest.ResultBase;
import com.example.hiot_clout.data.bean.UserBean;
import com.example.hiot_clout.ui.base.BasePresenter;
import com.example.hiot_clout.utils.Constants;

import javax.inject.Inject;

/**
 * 注册模块prerenter
 */
class RegisterPresenter extends BasePresenter<RegisterView> {

    @Inject
    DataManager dataManager;

    @Inject
    public RegisterPresenter() {

    }

    /**
     * 注册
     */
    public void register(String userName, String password, String email) {
        subscrib(dataManager.register(userName, password, email), new RequestCallback<ResultBase<UserBean>>() {
            @Override
            public void onNext(ResultBase<UserBean> resultBase) {
                if (resultBase.getStatus() == Constants.MSG_STATUS_SUCCESS) {
                    //判断如果注册成功，吐司注册成功
                    if (resultBase != null && resultBase.getData() != null) {
                        //弹出注册成功
                        getView().showMessage("注册成功");
                        //自动跳转，跳转到主界面
                        getView().registerSucc(email, password);
                    }
                } else {
                    //如果注册失败，吐司服务端返回的错误信息
                    if (resultBase != null && !TextUtils.isEmpty(resultBase.getMsg())) {
                        getView().showMessage(resultBase.getMsg());
                    }
                }


            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                getView().showMessage("当前网络无法访问，请稍后再试");
            }
        });
    }

    /**
     * 登录
     *
     * @param email
     * @param password
     */
    public void login(String email, String password) {
        subscrib(dataManager.login(email, password), new RequestCallback<ResultBase<LoginResultDTO>>() {
            @Override
            public void onNext(ResultBase<LoginResultDTO> resultBase) {
                if (resultBase.getStatus() == Constants.MSG_STATUS_SUCCESS) {
                    //如果登录成功
                    if (resultBase != null && resultBase.getData() != null) {
                        //弹出登录成功
//                        getView().showMessage("登录成功");
                        //跳转到主界面
                        getView().loginSucc();
                    }
                } else {
                    //如果登录失败，弹出服务端返回的错误信息
                    if (resultBase != null && !TextUtils.isEmpty(resultBase.getMsg())) {
                        getView().showMessage(resultBase.getMsg());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                getView().showMessage(Constants.TOAST_MSG_NETWORK_FALL);
            }
        });
    }
}

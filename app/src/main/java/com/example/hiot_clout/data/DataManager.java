package com.example.hiot_clout.data;

import com.example.hiot_clout.test.networktest.LoginResultDTO;
import com.example.hiot_clout.test.networktest.ResultBase;
import com.example.hiot_clout.test.networktest.UserBean;
import com.example.hiot_clout.utils.Constants;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;


/**
 * 网络请求封装类
 */
public class DataManager {

    private NetworkService service;

    SharedPreferencesHelper sharedPreferencesHelper;

    @Inject
    public DataManager(NetworkService service, SharedPreferencesHelper sharedPreferencesHelper) {
        this.service = service;
        this.sharedPreferencesHelper = sharedPreferencesHelper;
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    public Observable<ResultBase<LoginResultDTO>> login(String username, String password) {
        return service.login(username, password, Constants.LOGIN_CODE_APP)
                .doOnNext(new Consumer<ResultBase<LoginResultDTO>>() {
                    @Override
                    public void accept(ResultBase<LoginResultDTO> resultBase) throws Exception {
                        if (resultBase.getStatus() == Constants.MSG_STATUS_SUCCESS) {
                            if (resultBase != null && resultBase.getData() != null) {
                                sharedPreferencesHelper.setUserToken(resultBase.getData().getTokenValue());
                            }
                        }
                    }
                });
    }

    /**
     * 获取用户信息
     *
     * @param authorization
     * @return
     */
    public Observable<ResultBase<UserBean>> getUserinfo(String authorization) {
        return service.getUserinfo(authorization);
    }

    /**
     * 修改邮箱
     *
     * @param authorization
     * @param email
     * @return
     */
    public Observable<ResultBase<String>> updateEmail(String authorization, String email) {
        return service.updateEmail(authorization, email);
    }

    /**
     * 注册
     *
     * @param userName 用户名
     * @param password 用户密码
     * @param email    邮箱地址
     * @return
     */
    public Observable<ResultBase<UserBean>> register(String userName, String password, String email) {

        UserBean userBean = new UserBean();
        userBean.setUsername(userName);
        userBean.setPassword(password);
        userBean.setEmail(email);
        userBean.setUserType(Constants.REGISTER_TYPE_NORMAL);
        return service.register(userBean);
    }
}

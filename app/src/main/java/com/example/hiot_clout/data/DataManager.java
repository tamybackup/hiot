package com.example.hiot_clout.data;

import com.example.hiot_clout.test.networktest.LoginResultDTO;
import com.example.hiot_clout.test.networktest.ResultBase;
import com.example.hiot_clout.test.networktest.UserBean;
import com.example.hiot_clout.utils.Constants;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


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
     * @return
     */
    public Observable<ResultBase<UserBean>> getUserinfo() {
        return service.getUserinfo(sharedPreferencesHelper.getUserToken());
    }

    /**
     * 修改邮箱
     *
     * @param email
     * @return
     */
    public Observable<ResultBase<String>> updateEmail(String email) {
        return service.updateEmail(sharedPreferencesHelper.getUserToken(), email);
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

    /**
     * 上传图片
     *
     * @param filePath
     */
    public Observable<ResultBase<String>> uploadImage(String filePath) {
        File file = new File(filePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse(Constants.MULTIPART_FORM_DATA), file);
        MultipartBody.Part multipartFile = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        return service.uploadimage(multipartFile, sharedPreferencesHelper.getUserToken());
    }

    public Observable<ResultBase> logout() {
        return service.logout(sharedPreferencesHelper.getUserToken())
                .doOnNext(new Consumer<ResultBase>() {
                    @Override
                    public void accept(ResultBase resultBase) throws Exception {
                        sharedPreferencesHelper.setUserToken("");
                    }
                });
    }

    /**
     * 设备绑定
     * @param deviceId
     * @return
     */
    public Observable<ResultBase> bindDevice(String deviceId){
        return service.bindDevicec(deviceId,sharedPreferencesHelper.getUserToken());
    }
}

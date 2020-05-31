package com.example.hiot_clout.ui.mine;

import com.example.hiot_clout.data.DataManager;
import com.example.hiot_clout.test.networktest.ResultBase;
import com.example.hiot_clout.test.networktest.UserBean;
import com.example.hiot_clout.ui.base.BasePresenter;
import com.example.hiot_clout.utils.Constants;

import javax.inject.Inject;

/**
 * 用户中心presenter类
 */
public class MinePresenter extends BasePresenter<MineView> {

    @Inject
    DataManager dataManager;

    @Inject
    public MinePresenter() {
    }

    /**
     * 加载用户信息
     */
    public void loadUserInfo() {
        subscrib(dataManager.getUserinfo(), new RequestCallback<ResultBase<UserBean>>() {
            @Override
            public void onNext(ResultBase<UserBean> resultBase) {
                if (resultBase == null) {
                    getView().showMessage("服务器开小差了，请稍后再试");
                    return;
                }

                //如果token失效
                if (resultBase.getStatus() == Constants.MSG_STATUS_TOKEN_OUT) {
                    getView().tokenOut();
                    return;
                }

                if (resultBase.getStatus() != Constants.MSG_STATUS_SUCCESS) {
                    getView().showMessage(resultBase.getMsg());
                    return;
                }

                if (resultBase.getData() == null) {
                    getView().showMessage("服务器开小差了，请稍后再试");
                    return;
                }

                UserBean userBean = resultBase.getData();
                getView().refreshUserInfo(userBean);
            }
        });
    }

    /**
     * 上传用户头像
     *
     * @param filePath
     */
    public void uploadImage(String filePath) {
        subscrib(dataManager.uploadImage(filePath), new RequestCallback<ResultBase<String>>() {
            @Override
            public void onNext(ResultBase<String> resultBase) {
                if (resultBase == null) {
                    getView().showMessage("服务器开小差了，请稍后再试");
                    return;
                }

                //如果token失效
                if (resultBase.getStatus() == Constants.MSG_STATUS_TOKEN_OUT) {
                    getView().tokenOut();
                    return;
                }

                if (resultBase.getStatus() != Constants.MSG_STATUS_SUCCESS) {
                    getView().showMessage(resultBase.getMsg());
                    return;
                }

                //获取相对路径
                String url = resultBase.getData();

                //刷新用户头像
                getView().refreshUserHead(url);
            }
        });
    }

    /**
     * 注销方法
     */
    public void logout() {
        subscrib(dataManager.logout(), new RequestCallback<ResultBase>() {
            @Override
            public void onNext(ResultBase resultBase) {
                if (resultBase == null) {
                    getView().showMessage("服务器开小差了，请稍后再试");
                    return;
                }

                //如果token失效
                if (resultBase.getStatus() == Constants.MSG_STATUS_TOKEN_OUT) {
                    getView().tokenOut();
                    return;
                }

                if (resultBase.getStatus() != Constants.MSG_STATUS_SUCCESS) {
                    getView().showMessage(resultBase.getMsg());
                    return;
                }

                getView().tokenOut();
            }
        });
    }
}

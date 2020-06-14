package com.example.hiot_clout.ui.devicedetail;

import com.example.hiot_clout.data.DataManager;
import com.example.hiot_clout.data.bean.DeviceDetailBean;
import com.example.hiot_clout.test.networktest.ResultBase;
import com.example.hiot_clout.ui.base.BasePresenter;
import com.example.hiot_clout.utils.Constants;

import javax.inject.Inject;

/**
 * 设备详情模块presenter类
 */
class DeviceDetailPresenter extends BasePresenter<DeviceDetailView> {

    @Inject
    DataManager dataManager;

    @Inject
    public DeviceDetailPresenter() {
    }

    /**
     * 加载设备详情
     *
     * @param deviceId
     */
    public void loadDeviceDetail(String deviceId) {
        subscrib(dataManager.getDeviceDetail(deviceId), new RequestCallback<ResultBase<DeviceDetailBean>>() {
            @Override
            public void onNext(ResultBase<DeviceDetailBean> resultBase) {
                super.onNext(resultBase);
                if (resultBase.getData() == null) {
                    getView().showMessage("无设备详情");
                }

                getView().setDeviceDetail(resultBase.getData());
            }
        });
    }

    /**
     * 控制开关通道状态
     *
     * @param downDataStreamId
     * @param isChecked
     */
    public void changeSwitch(String downDataStreamId, boolean isChecked) {
        int status = isChecked ? Constants.SWITCH_STATUS_ON : Constants.SWITCH_STATUS_OFF;
        subscrib(dataManager.changeSwitch(downDataStreamId, status), new RequestCallback<ResultBase>() {
            @Override
            public void onNext(ResultBase resultBase) {
                super.onNext(resultBase);
                getView().showMessage("操作成功");
            }
        });
    }
}

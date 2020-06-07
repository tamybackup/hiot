package com.example.hiot_clout.ui.scan;

import com.example.hiot_clout.data.DataManager;
import com.example.hiot_clout.test.networktest.ResultBase;
import com.example.hiot_clout.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * 扫一扫模块Presenter类
 */
class ScanPresenter extends BasePresenter<ScanView> {

    @Inject
    DataManager dataManager;

    @Inject
    public ScanPresenter() {
    }

    /**
     * 绑定设备
     * @param deviceId
     */
    public void bindDevice(String deviceId) {
subscrib(dataManager.bindDevice(deviceId), new RequestCallback<ResultBase>() {
    @Override
    public void onNext(ResultBase resultBase) {
        super.onNext(resultBase);
        getView().showMessage("绑定成功");
        getView().bindDeviceSucc();
    }
});
    }
}

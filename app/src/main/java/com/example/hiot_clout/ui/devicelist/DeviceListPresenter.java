package com.example.hiot_clout.ui.devicelist;

import com.example.hiot_clout.data.DataManager;
import com.example.hiot_clout.data.bean.DeviceBean;
import com.example.hiot_clout.test.networktest.ResultBase;
import com.example.hiot_clout.ui.base.BasePresenter;
import com.example.hiot_clout.utils.Constants;

import java.util.List;

import javax.inject.Inject;

/**
 * 设备列表Presenter类
 */
class DeviceListPresenter extends BasePresenter<DeviceListView> {
    @Inject
    DataManager dataManager;

    @Inject
    public DeviceListPresenter() {
    }

    public void loadDeviceList() {
        subscrib(dataManager.listBindedDevice(Constants.DEVICE_STATUS_BINDED), new RequestCallback<ResultBase<List<DeviceBean>>>() {
            @Override
            public void onNext(ResultBase<List<DeviceBean>> listResultBase) {
                super.onNext(listResultBase);
                List<DeviceBean> deviceList = listResultBase.getData();
                getView().showDeviceList(deviceList);
            }
        });
    }
}

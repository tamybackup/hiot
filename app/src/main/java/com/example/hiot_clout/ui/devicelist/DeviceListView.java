package com.example.hiot_clout.ui.devicelist;

import com.example.hiot_clout.data.bean.DeviceBean;
import com.example.hiot_clout.ui.base.BaseView;

import java.util.List;

/**
 * 设备列表View层接口
 */
interface DeviceListView extends BaseView {
    /**
     * 显示设备列表
     * @param deviceList
     */
    void showDeviceList(List<DeviceBean> deviceList);

}

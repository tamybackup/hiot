package com.example.hiot_clout.ui.devicedetail;

import com.example.hiot_clout.data.bean.DeviceDetailBean;
import com.example.hiot_clout.ui.base.BaseView;

/**
 * 设备详情view层接口
 */
interface DeviceDetailView extends BaseView {
    /**
     * 根据设备详情内容显示到界面
     * @param data
     */
    void setDeviceDetail(DeviceDetailBean data);
}

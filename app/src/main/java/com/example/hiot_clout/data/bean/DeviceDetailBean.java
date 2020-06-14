package com.example.hiot_clout.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 设备详情对象
 */
public class DeviceDetailBean implements Serializable {

    private String deviceid;

    private String title;
    private String status;
    private String deviceimg;
    private List<UpdatastreamDataDto> updatastreamDataDtoList;

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeviceimg() {
        return deviceimg;
    }

    public void setDeviceimg(String deviceimg) {
        this.deviceimg = deviceimg;
    }

    public List<UpdatastreamDataDto> getUpdatastreamDataDtoList() {
        return updatastreamDataDtoList;
    }

    public void setUpdatastreamDataDtoList(List<UpdatastreamDataDto> updatastreamDataDtoList) {
        this.updatastreamDataDtoList = updatastreamDataDtoList;
    }
}

package com.example.hiot_clout.data.bean;

import java.io.Serializable;

/**
 * 设备对象
 */
public class DeviceBean implements Serializable {

    /**
     * 设备id
     */
    private String id;

    /**
     * 设备标题
     */
    private String title;

    /**
     * 设备类型
     */
    private String dev_type;

    /**
     * mac地址
     */
    private String mac;

    /**
     * 设备状态
     */
    private String status;

    /**
     * 创建时间
     */
    private String created;

    /**
     * 修改时间
     */
    private String updated;

    /**
     * 图片地址
     */
    private String deviceimg;

    /**
     * 描述
     */
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDev_type() {
        return dev_type;
    }

    public void setDev_type(String dev_type) {
        this.dev_type = dev_type;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getDeviceimg() {
        return deviceimg;
    }

    public void setDeviceimg(String deviceimg) {
        this.deviceimg = deviceimg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

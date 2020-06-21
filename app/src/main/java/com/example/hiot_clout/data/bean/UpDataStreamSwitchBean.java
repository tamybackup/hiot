package com.example.hiot_clout.data.bean;

import java.io.Serializable;

/**
 * 开关通道对象
 */
public class UpDataStreamSwitchBean implements Serializable  {
    private String updatastreamId;
    private String timing;
    private Integer status;

    public String getUpdatastreamId() {
        return updatastreamId;
    }

    public void setUpdatastreamId(String updatastreamId) {
        this.updatastreamId = updatastreamId;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

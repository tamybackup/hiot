package com.example.hiot_clout.data.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 开关通道对象
 */
public class SwitchBean implements Serializable  {
    private String downDataStreamId;
    private String timing;
    private Integer status;

    public String getDownDataStreamId() {
        return downDataStreamId;
    }

    public void setDownDataStreamId(String downDataStreamId) {
        this.downDataStreamId = downDataStreamId;
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

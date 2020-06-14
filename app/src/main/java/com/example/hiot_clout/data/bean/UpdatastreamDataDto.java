package com.example.hiot_clout.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 设备详情通道列表
 */
public class UpdatastreamDataDto implements Serializable {
    private String upDataStreamId;
    private String title;
    private String data_type;
    private List<SwitchBean> dataList;

    public String getUpDataStreamId() {
        return upDataStreamId;
    }

    public void setUpDataStreamId(String upDataStreamId) {
        this.upDataStreamId = upDataStreamId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public List<SwitchBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<SwitchBean> dataList) {
        this.dataList = dataList;
    }
}

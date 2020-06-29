package com.example.hiot_clout.ui.gpsdatastreamhistory;

import com.example.hiot_clout.data.bean.UpDataStreamGpsBean;
import com.example.hiot_clout.ui.base.BaseView;

import java.util.List;

interface GpsDataStreamHistoryView extends BaseView {
    /**
     * 显示数据
     * @param data
     */
    void showData(List<UpDataStreamGpsBean> data);
}

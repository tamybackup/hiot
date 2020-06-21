package com.example.hiot_clout.ui.datastreamhistory;


import com.example.hiot_clout.data.bean.UpDataStreamSwitchBean;
import com.example.hiot_clout.ui.base.BaseView;

import java.util.List;

/**
 * 通道历史数据View层接口
 */
interface LineChartView extends BaseView {
    /**
     * 把返回的通道历史数据显示到图标中
     * @param dataList
     */
    void showDataHistory(List<UpDataStreamSwitchBean> dataList);
}

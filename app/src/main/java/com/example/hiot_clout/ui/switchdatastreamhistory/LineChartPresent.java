package com.example.hiot_clout.ui.switchdatastreamhistory;

import com.example.hiot_clout.data.DataManager;
import com.example.hiot_clout.data.bean.UpDataStreamSwitchBean;
import com.example.hiot_clout.test.networktest.ResultBase;
import com.example.hiot_clout.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * 通道历史数据presenter类
 */
class LineChartPresenter extends BasePresenter<LineChartView> {
    @Inject
    DataManager dataManager;

    @Inject
    public LineChartPresenter() {
    }

    /**
     * 加载通道历史数据
     * @param upDateStreamId
     */
    public void loadUpDataStreamHistory(String upDateStreamId) {
        subscrib(dataManager.getSwicthUpdataStreamHistory(upDateStreamId), new RequestCallback<ResultBase<List<UpDataStreamSwitchBean>>>() {
            @Override
            public void onNext(ResultBase<List<UpDataStreamSwitchBean>> resultBase) {
                super.onNext(resultBase);
                if (resultBase.getData() != null){
                    getView().showDataHistory(resultBase.getData());
                }
            }
        });
    }
}

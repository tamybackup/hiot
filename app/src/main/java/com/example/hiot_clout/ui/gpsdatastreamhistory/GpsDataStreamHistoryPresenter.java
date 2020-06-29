package com.example.hiot_clout.ui.gpsdatastreamhistory;

import com.example.hiot_clout.data.DataManager;
import com.example.hiot_clout.data.bean.UpDataStreamGpsBean;
import com.example.hiot_clout.test.networktest.ResultBase;
import com.example.hiot_clout.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

class GpsDataStreamHistoryPresenter extends BasePresenter<GpsDataStreamHistoryView> {
    @Inject
    DataManager dataManager;

    @Inject
    public GpsDataStreamHistoryPresenter() {
    }

    public void loadGpsDataStreamHistory(String upDataStreamId) {
        subscrib(dataManager.getGpsUpdataStreamHistory(upDataStreamId), new RequestCallback<ResultBase<List<UpDataStreamGpsBean>>>() {
            @Override
            public void onNext(ResultBase<List<UpDataStreamGpsBean>> resultBase) {
                super.onNext(resultBase);
                if (resultBase.getData() != null){
                    getView().showData(resultBase.getData());
                }
            }
        });
    }
}

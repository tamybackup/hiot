package com.example.hiot_clout.ui.switchdatastreamhistory;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.hiot_clout.R;
import com.example.hiot_clout.data.bean.UpDataStreamSwitchBean;
import com.example.hiot_clout.ui.base.BaseActivity;
import com.example.hiot_clout.utils.Constants;
import com.example.hiot_clout.utils.MPChartHelper;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LineChartActivity extends BaseActivity<LineChartView, LineChartPresenter> implements LineChartView {

    @BindView(R.id.tv_data_history)
    TextView tvDataHistory;

    @BindView(R.id.tool_bar_history)
    Toolbar toolBarHistory;

    @BindView(R.id.line_chart_history)
    LineChart lineChartHistory;

    /**
     * 上行通道id
     */
    private String upDateStreamId;

    @Inject
    LineChartPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        ButterKnife.bind(this);
        upDateStreamId = getIntent().getStringExtra(Constants.INTENT_EXTRA_UP_DATA_STREAM_ID);
        initView();
    }

    @Override
    public LineChartPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadUpDataStreamHistory(upDateStreamId);
    }

    @Override
    public void showDataHistory(List<UpDataStreamSwitchBean> dataList) {
        if (dataList == null || dataList.isEmpty()) {
            return;
        }
        List<String> xAxisValue = new ArrayList<>();
        List<Float> yAxisValue = new ArrayList<>();
        List<String> timing = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            xAxisValue.add(String.valueOf(i));
            yAxisValue.add(Float.valueOf(dataList.get(i).getStatus()));
            timing.add(dataList.get(i).getTiming());
        }

        lineChartHistory.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                showMessage(timing.get((int)e.getY()));
            }

            @Override
            public void onNothingSelected() {

            }
        });

        MPChartHelper.setSingleLineChart(lineChartHistory, xAxisValue, yAxisValue, timing, "电平图 0：代表关 1：代表开", true, true);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        setSupportActionBar(toolBarHistory);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBarHistory.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

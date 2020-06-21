package com.example.hiot_clout.utils;

import android.graphics.Color;

import com.example.hiot_clout.R;
import com.example.hiot_clout.widget.mpcahrt.MPChartMarkerView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class MPChartHelper {
    /**
     * 显示折线图方法定义
     */
    public static void setSingleLineChart(LineChart lineChart, List<String> xAxisValue, List<Float> yAxisValue,
                                          List<String> timing, String title, boolean showSetValues, boolean isSwitch) {
        //初始化参数
        initLineChart(lineChart, xAxisValue, yAxisValue, title, showSetValues);

        //构造entryList
        List<Entry> entryList = new ArrayList<>();
        for (int i = 0; i < yAxisValue.size(); i++) {
            Entry entry = new Entry(i, yAxisValue.get(i));
            entryList.add(entry);
        }

        //给控件赋值
        LineData lineData = lineChart.getLineData();
        if (lineData == null) {
            lineData = new LineData();
        }

        List<ILineDataSet> dataSets = lineData.getDataSets();
        if (dataSets == null || dataSets.isEmpty()) {
            LineDataSet lineDataSet = new LineDataSet(entryList, title);
            lineDataSet.setMode(isSwitch ? LineDataSet.Mode.STEPPED : LineDataSet.Mode.LINEAR);
            lineDataSet.setColor(Color.BLUE);
            lineData.addDataSet(lineDataSet);
            lineChart.setData(lineData);
        } else {
            LineDataSet lineDataSet = (LineDataSet) dataSets.get(0);
            lineDataSet.setValues(entryList);
            lineDataSet.setLabel(title);
            lineData.notifyDataChanged();
            lineDataSet.setMode(isSwitch ? LineDataSet.Mode.STEPPED : LineDataSet.Mode.LINEAR);
            lineDataSet.setColor(Color.BLUE);
            lineChart.notifyDataSetChanged();
        }
    }

    public static void initLineChart(LineChart lineChart, List<String> xAxisValue, List<Float> yAxisValue,
                                     String title, boolean showSetValues) {

        //添加marker
        MPChartMarkerView markerView = new MPChartMarkerView(lineChart.getContext(), R.layout.custom_marker_view);
        lineChart.setMarker(markerView);

        //设置属性
        lineChart.getDescription().setEnabled(false);//设置不显示描述
        lineChart.setPinchZoom(true);//设置按比例放折线图

        //x坐标轴设置
        XAxis xAxis = lineChart.getXAxis();
        //X轴在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //不绘制x轴网格
        xAxis.setDrawGridLines(false);
        //设置最小间隔，防止当放大时，出现重复标签
        xAxis.setGranularity(1f);
        //设置x轴标签个数
        xAxis.setLabelCount(xAxisValue.size());

        //左侧y轴设置
        YAxis leftAxis = lineChart.getAxisLeft();
        //y轴标签绘制的位置
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        //不绘制y轴网格
        leftAxis.setDrawGridLines(false);
        if (showSetValues) {
            leftAxis.setDrawLabels(false);//折线上显示值，则不显示坐标轴上的值
        }
        //设置不显示右边的Y轴
        lineChart.getAxisRight().setEnabled(false);
        //图例设置
        Legend legend = lineChart.getLegend();
        //设置图例位置 chart的正上方，水平显示
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        //设置图例位置 chart的正上方，水平显示
        legend.setForm(Legend.LegendForm.LINE);
        //设置图例文字大小
        legend.setTextSize(12f);

        //设置视图窗口大小
        lineChart.setExtraOffsets(10, 30, 20, 10);
        //可触摸
        lineChart.setTouchEnabled(true);
        // 可拖曳
        lineChart.setDragEnabled(true);
        //一个界面最多显示6个点，其他点可以通过滑动看到
        lineChart.setVisibleXRangeMaximum(5);
        //一个页面最少显示4个点
        lineChart.setVisibleXRangeMinimum(3);
        // 可缩放
        lineChart.setScaleEnabled(true);
        //数据显示动画，从左往右依次显示
        lineChart.animateX(1000);
    }
}

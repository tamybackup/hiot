package com.example.hiot_clout.ui.devicedetail;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.hiot_clout.R;
import com.example.hiot_clout.data.bean.DeviceDetailBean;
import com.example.hiot_clout.data.bean.SwitchBean;
import com.example.hiot_clout.data.bean.UpdatastreamDataDto;
import com.example.hiot_clout.ui.base.BaseActivity;
import com.example.hiot_clout.ui.gpsdatastreamhistory.GpsDataStreamHistoryActivity;
import com.example.hiot_clout.ui.switchdatastreamhistory.LineChartActivity;
import com.example.hiot_clout.utils.Constants;
import com.example.hiot_clout.utils.ImageUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeviceDetailActivity extends BaseActivity<DeviceDetailView, DeviceDetailPresenter> implements DeviceDetailView {

    @Inject
    DeviceDetailPresenter presenter;

    @BindView(R.id.iv_device_detail)
    ImageView ivDeviceDetail;

    @BindView(R.id.tv_device_title)
    TextView tvDeviceTitle;

    @BindView(R.id.tv_device_state)
    TextView tvDeviceState;

    @BindView(R.id.toolbar_device)
    Toolbar toolbarDevice;

    @BindView(R.id.iv_data_stream_history)
    ImageView ivDataStreamHistory;

    @BindView(R.id.tv_data_stream_type)
    TextView tvDataStreamType;

    @BindView(R.id.iv_switch)
    ImageView ivSwitch;

    @BindView(R.id.switch_data_stream)
    Switch switchDataStream;

    @BindView(R.id.srl_device_detail)
    SwipeRefreshLayout srlDeviceDetail;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 当前上行通道id
     */
    private String upDataStreamId;

    /**
     * 当前通道类型
     */
    private String upDataStreamType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_detail);
        ButterKnife.bind(this);
        initView();
        deviceId = getIntent().getStringExtra(Constants.INTENT_EXTRA_DEVICE_ID);
    }


    @Override
    public DeviceDetailPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDeviceDetail();
    }


    /**
     * 初始化控件
     */
    private void initView() {
        //下拉刷新
        srlDeviceDetail.setColorSchemeColors(getResources().getColor(android.R.color.holo_red_dark),
                getResources().getColor(android.R.color.holo_blue_dark),
                getResources().getColor(android.R.color.holo_green_dark)
        );

        srlDeviceDetail.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadDeviceDetail();
            }
        });

        //toolbar
        setSupportActionBar(toolbarDevice);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarDevice.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 加载设备详情
     */
    private void loadDeviceDetail() {
        srlDeviceDetail.setRefreshing(true);

        presenter.loadDeviceDetail(deviceId);

        srlDeviceDetail.setRefreshing(false);

    }

    @Override
    public void setDeviceDetail(DeviceDetailBean data) {
        if (data == null) {
            return;
        }
        tvDeviceTitle.setText(data.getTitle());
        tvDeviceState.setText(Constants.DEVICE_STATUS_ACTIVITY.equals(data.getStatus()) ? "已激活" : "未激活");
        ImageUtils.show(this, ivDeviceDetail, ImageUtils.getFullUrl(data.getDeviceimg()));

        //解析通道信息
        switchDataStream.setVisibility(View.GONE);
        if (data.getUpdatastreamDataDtoList() != null && !data.getUpdatastreamDataDtoList().isEmpty()) {
            UpdatastreamDataDto updatastreamDataDto = data.getUpdatastreamDataDtoList().get(0);
            if (updatastreamDataDto == null) {
                return;
            }

            upDataStreamId = null;
            upDataStreamType = updatastreamDataDto.getData_type();
            if (Constants.DATA_STREAM_TYPE_GPS.equals(updatastreamDataDto.getData_type())) {
                upDataStreamId = updatastreamDataDto.getUpDataStreamId();
                tvDataStreamType.setText("GPS通道");
            }

            if (Constants.DATA_STREAM_TYPE_SWITCH.equals(updatastreamDataDto.getData_type())) {
                upDataStreamId = updatastreamDataDto.getUpDataStreamId();
                tvDataStreamType.setText("开关通道");
                switchDataStream.setVisibility(View.VISIBLE);
                if (updatastreamDataDto.getDataList() != null && !updatastreamDataDto.getDataList().isEmpty()) {
                    SwitchBean switchBean = updatastreamDataDto.getDataList().get(0);
                    if (switchBean == null) {
                        return;
                    }
                    //如果开关状态开
                    if (Constants.SWITCH_STATUS_ON == switchBean.getStatus()) {
                        ivSwitch.setImageResource(R.drawable.icon_on);
                        switchDataStream.setChecked(true);
                    } else {
                        ivSwitch.setImageResource(R.drawable.icon_off);
                        switchDataStream.setChecked(false);
                    }
                    //设置switch事件
                    switchDataStream.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            presenter.changeSwitch(switchBean.getDownDataStreamId(), isChecked);
                        }
                    });
                }
            }
        }
    }

    @OnClick(R.id.iv_data_stream_history)
    public void onViewClicked() {
        if (TextUtils.isEmpty(upDataStreamId)) {
            return;
        }
        if (Constants.DATA_STREAM_TYPE_SWITCH.equals(upDataStreamType)) {
            Intent intent = new Intent(this, LineChartActivity.class);
            intent.putExtra(Constants.INTENT_EXTRA_UP_DATA_STREAM_ID, upDataStreamId);
            startActivity(intent);
            return;
        }
        if (Constants.DATA_STREAM_TYPE_GPS.equals(upDataStreamType)) {
            Intent intent = new Intent(this, GpsDataStreamHistoryActivity.class);
            intent.putExtra(Constants.INTENT_EXTRA_UP_DATA_STREAM_ID, upDataStreamId);
            startActivity(intent);
        }
    }
}

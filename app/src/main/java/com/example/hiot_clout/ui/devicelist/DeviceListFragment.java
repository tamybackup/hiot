package com.example.hiot_clout.ui.devicelist;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.hiot_clout.R;
import com.example.hiot_clout.data.bean.DeviceBean;
import com.example.hiot_clout.ui.base.BaseActivity;
import com.example.hiot_clout.ui.base.BaseFragment;
import com.example.hiot_clout.ui.devicedetail.DeviceDetailActivity;
import com.example.hiot_clout.ui.scan.ScanActivity;
import com.example.hiot_clout.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class DeviceListFragment extends BaseFragment<DeviceListView, DeviceListPresenter> implements DeviceListView {

    @Inject
    DeviceListPresenter presenter;

    @BindView(R.id.iv_add)
    ImageView ivDeeviceList;

    @BindView(R.id.rv_device_list)
    RecyclerView rvDeviceList;

    @BindView(R.id.srl_device_list)
    SwipeRefreshLayout srlDeviceList;

    @BindView(R.id.tv_device_list_nodata)
    TextView tvDeviceListNodata;
    private DeviceListAdapter deviceListAdapter;

    public static DeviceListFragment newInstance() {
        DeviceListFragment fragment = new DeviceListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public DeviceListPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void injectDependencies() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).getActivityComponent().inject(this);
        }
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_device_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initWidget();
    }


    @OnClick(R.id.iv_add)
    public void onViewClicked() {
        startActivityWithoutFinish(ScanActivity.class);
    }

    /**
     * 初始化控件
     */
    private void initWidget() {
        //初始化文本框
        tvDeviceListNodata.setVisibility(View.VISIBLE);

        //初始化下拉控件
        srlDeviceList.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_red_dark),
                getResources().getColor(android.R.color.holo_green_dark),
                getResources().getColor(android.R.color.holo_blue_dark)
        );
        srlDeviceList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadDeviceList();
            }
        });

        //初始化列表
        rvDeviceList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvDeviceList.setHasFixedSize(true);
        deviceListAdapter = new DeviceListAdapter(getActivity());
        deviceListAdapter.setOnItemClickListener(new DeviceListAdapter.OnItemClickListener() {
            @Override
            public void onClickListener(DeviceBean bean) {
                if (bean == null){
                    return;
                }
                Intent intent=new Intent(getActivity(), DeviceDetailActivity.class);
                intent.putExtra(Constants.INTENT_EXTRA_DEVICE_ID,bean.getId());
                startActivity(intent);
            }
        });
        rvDeviceList.setAdapter(deviceListAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadDeviceList();
    }

    /**
     * 加载设备列表
     */
    private void loadDeviceList() {
        srlDeviceList.setRefreshing(true);
        presenter.loadDeviceList();
        srlDeviceList.setRefreshing(false);
    }

    @Override
    public void showDeviceList(List<DeviceBean> deviceList) {
        if (deviceList !=null && !deviceList.isEmpty()){
            tvDeviceListNodata.setVisibility(View.GONE);
        }else{
            tvDeviceListNodata.setVisibility(View.VISIBLE);
        }
        deviceListAdapter.setData(deviceList);
    }
}

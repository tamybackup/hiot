package com.example.hiot_clout.ui.devicelist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hiot_clout.R;
import com.example.hiot_clout.data.bean.DeviceBean;
import com.example.hiot_clout.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设备适配器
 */
public class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.ViewHolder> {

    private Context context;
    private OnItemClickListener onItemClickListener;

    private List<DeviceBean> dataList = new ArrayList<>();

    public DeviceListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //创建itemView
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.adapter_item_device, parent, false);

        //创建ViewHolder
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.bindData(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeviceBean bean = dataList.get(position);
                onItemClickListener.onClickListener(bean);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("tag", "size:" + dataList.size());
        return dataList.size();
    }

    /**
     * 设置列表
     *
     * @param list
     */
    public void setData(List<DeviceBean> list) {
        dataList.clear();
        if (list!=null && !list.isEmpty()) {
            dataList.addAll(list);
        }
        notifyDataSetChanged();
    }

    /**
     * 获取列表
     *
     * @return
     */
    public List<DeviceBean> getData() {
        return dataList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_device)
        ImageView ivDevice;

        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.tv_desc)
        TextView tvDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        /**
         * 修改列表项内容
         *
         * @param position
         */
        public void bindData(int position) {
            if (position<dataList.size()){
                DeviceBean bean = dataList.get(position);
                tvTitle.setText(bean.getTitle());
                tvDesc.setText(bean.getDescription());
                ImageUtils.show(context,ivDevice,ImageUtils.getFullUrl(bean.getDeviceimg()));
            }
        }
    }

    /**
     * 点击事件接口
     */
    public interface OnItemClickListener {
        void onClickListener(DeviceBean bean);
    }

    /**
     * 提供点击实现类方法
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

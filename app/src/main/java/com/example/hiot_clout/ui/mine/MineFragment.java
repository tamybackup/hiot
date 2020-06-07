package com.example.hiot_clout.ui.mine;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hiot_clout.R;
import com.example.hiot_clout.data.bean.UserBean;
import com.example.hiot_clout.ui.base.BaseActivity;
import com.example.hiot_clout.ui.base.BaseFragment;
import com.example.hiot_clout.ui.login.LoginActivity;
import com.example.hiot_clout.utils.ImageUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MineFragment extends BaseFragment<MineView, MinePresenter> implements MineView {

    @Inject
    MinePresenter presenter;

    @BindView(R.id.iv_head_image)
    ImageView ivHeadImage;

    @BindView(R.id.tv_user_center_nickname)
    TextView tvUserCenterNickname;

    @BindView(R.id.tv_user_center_email)
    TextView tvUserCenterEmail;

    @BindView(R.id.tv_user_center_update_password)
    TextView tvUserCenterUpdatePassword;

    @BindView(R.id.tv_user_center_update_email)
    TextView tvUserCenterUpdateEmail;

    @BindView(R.id.btn_logout)
    Button btnLogout;

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public MinePresenter createPresenter() {
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
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.loadUserInfo();
    }

    @OnClick({R.id.iv_head_image, R.id.tv_user_center_update_password, R.id.tv_user_center_update_email, R.id.btn_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_head_image:
                checkPermission();
                break;
            case R.id.tv_user_center_update_password:
                break;
            case R.id.tv_user_center_update_email:
                break;
            case R.id.btn_logout:
                //注销
                presenter.logout();
                break;
        }
    }

    /**
     * 动态授权
     */
    private void checkPermission() {
        Acp.getInstance(getActivity()).request(
                new AcpOptions.Builder().setPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                ).build(),
                new AcpListener() {
                    @Override
                    public void onGranted() {
                        //用户同意授权，选择相册
                        ChoosePicture();
                    }

                    @Override
                    public void onDenied(List<String> permissions) {
                        showMessage("用户拒绝授权");
                    }
                }
        );
    }

    /**
     * 选择图片
     */
    private void ChoosePicture() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                //.theme()//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                .maxSelectNum(9)// 最大图片选择数量 int
                .minSelectNum(1)// 最小选择数量 int
                .imageSpanCount(3)// 每行显示个数 int
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                //.previewVideo()// 是否可预览视频 true or false
                //.enablePreviewAudio() // 是否可播放音频 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .imageFormat(PictureMimeType.JPEG)// 拍照保存图片格式后缀,默认jpeg
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                .enableCrop(true)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                //.glideOverride()// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示 true or false
                //.isGif()// 是否显示gif图片 true or false
                //.compressSavePath(getPath())//压缩图片保存地址
                //.freeStyleCropEnabled()// 裁剪框是否可拖拽 true or false
                .circleDimmedLayer(true)// 是否圆形裁剪 true or false
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                //.openClickSound()// 是否开启点击声音 true or false
                //.selectionMedia(selectedImage)// 是否传入已选图片 List<LocalMedia> list
                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                //.cropCompressQuality()// 裁剪压缩质量 默认90 int
                //.minimumCompressSize(100)// 小于100kb的图片不压缩
                //.synOrAsy(true)//同步true或异步false 压缩 默认同步
                //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                //.videoQuality()// 视频陆制质量 0 or 1 int
                //.videoMaxSecond(15)// 显示多少秒以内的视频or音频也可适用 int
                //.videoMinSecond(10)// 显示多少秒以内的视频or音频也可适用 int
                //.recordVideoSecond()//视频秒数陆制 默认60s int
                .isDragFrame(true)// 是否可拖动裁剪框(固定)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_CANCELED) {
            showMessage("用户取消");
        }
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PictureConfig.CHOOSE_REQUEST) {
                List<LocalMedia> imageList = PictureSelector.obtainMultipleResult(data);
                if (imageList != null && !imageList.isEmpty()) {
                    String filePath = imageList.get(0).getCompressPath();
                    presenter.uploadImage(filePath);
                }
            }
        }
    }

    @Override
    public void refreshUserInfo(UserBean userBean) {
        ImageUtils.showCircle(getActivity(), ivHeadImage, ImageUtils.getFullUrl(userBean.getImg()));
        tvUserCenterEmail.setText(userBean.getEmail());
        tvUserCenterNickname.setText(userBean.getUsername());
    }

    @Override
    public void refreshUserHead(String url) {
        ImageUtils.showCircle(getActivity(), ivHeadImage, ImageUtils.getFullUrl(url));
    }

    @Override
    public void tokenOut() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}

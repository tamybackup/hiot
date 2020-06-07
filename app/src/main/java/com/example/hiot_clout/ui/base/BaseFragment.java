package com.example.hiot_clout.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hiot_clout.ui.login.LoginActivity;

import butterknife.ButterKnife;

public abstract class BaseFragment<V extends BaseView, P extends BasePresenter<V>> extends Fragment implements BaseView {

    private P presenter;

    public abstract P createPresenter();

    public abstract void injectDependencies();

    public abstract View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = initView(inflater, container, savedInstanceState);
        injectDependencies();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = createPresenter();
        if (presenter != null) {
            presenter.setView((V) this);
        }
        ButterKnife.bind(this, view);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (presenter != null) {
            presenter.destroy();
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 打开新界面，关闭本界面
     * @param cls
     */
    protected void startActivity(Class<?> cls){
        Intent intent=new Intent(getActivity(),cls);
        startActivity(intent);
        getActivity().finish();
    }

    /**
     * 打开新界面，保留本界面
     * @param cls
     */
    protected void startActivityWithoutFinish(Class<?> cls){
        Intent intent=new Intent(getActivity(),cls);
        startActivity(intent);
    }

    @Override
    public void tokenOut() {
        startActivity(LoginActivity.class);
    }
}

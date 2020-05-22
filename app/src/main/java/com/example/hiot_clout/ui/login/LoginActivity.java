package com.example.hiot_clout.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.example.hiot_clout.R;
import com.example.hiot_clout.ui.base.BaseActivity;
import com.example.hiot_clout.ui.main.MainActivity;
import com.example.hiot_clout.utils.LoadingUtil;
import com.example.hiot_clout.utils.ValidatorUtils;
import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 登录模块活动类
 */
public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {

    @Inject
    LoginPresenter presenter;

    @BindView(R.id.tiptet_email)
    TextInputEditText tiptetEmail;

    @BindView(R.id.tiptet_password)
    TextInputEditText tiptetPassword;

    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

//        tiptetEmail = findViewById(R.id.tiptet_email);
//        tiptetPassword = findViewById(R.id.tiptet_password);
//        Button btnLogin = findViewById(R.id.btn_login);
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //如果校验成功，则保存登录状态，跳转到列表界面
//                String email = tiptetEmail.getText().toString();
//                String password = tiptetPassword.getText().toString();
//                if (ValidateSucc(email, password)){
//                    //请求服务端身份验证
//                    LoadingUtil.showLoading(LoginActivity.this, "正在登录...");
//                    presenter.login(email, password);
//
//                }
//            }
//        });
    }

    @Override
    public LoginPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);
    }

    /**
     * 校验用户输入
     *
     * @param email
     * @param password
     * @return
     */
    private boolean ValidateSucc(String email, String password) {
        //校验邮箱非空
        if (TextUtils.isEmpty(email)) {
            tiptetEmail.setError("邮箱不能为空，请重新输入");
            return false;
        }
        //校验邮箱合规
        if (!ValidatorUtils.isEmail(email)) {
            tiptetEmail.setError("邮箱输入不正确，请重新输入");
            return false;
        }

        //校验密码非空
        if (TextUtils.isEmpty(password)) {
            tiptetPassword.setError("密码不能为空，请重新输入");
            return false;
        }

        //校验密码合规
        if (!ValidatorUtils.isPassword(password)) {
            tiptetPassword.setError("密码输入不正确，请重新输入");
            return false;
        }


        return true;
    }

    @Override
    public void loginSucc() {
        //跳转到主界面
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_login)
    public void onClick(View view) {
        //如果校验成功，则保存登录状态，跳转到列表界面
        String email = tiptetEmail.getText().toString();
        String password = tiptetPassword.getText().toString();
        if (ValidateSucc(email, password)) {
            //请求服务端身份验证
            LoadingUtil.showLoading(LoginActivity.this, "正在登录...");
            presenter.login(email, password);

        }
    }
}

package com.example.hiot_clout.test.networktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hiot_clout.R;
import com.example.hiot_clout.data.bean.UserBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestRetrofitActivity extends AppCompatActivity {

    private static final String TAG = "TestRetrofitActivity";
    private Retrofit retrofit;
    private TestRetrofitService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_retrofit);

        createRertofit();

        Button btnTest = findViewById(R.id.btn_retrofit_enqueue);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test();
            }
        });

        Button btnLogin = findViewById(R.id.btn_retrofit_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login("zhangdaxia", "abc123", "app");
            }
        });

        Button btnUserinfo = findViewById(R.id.btn_retrofit_userinfo);
        btnUserinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserinfo("27bb6c07007e4f178f85c8db1f9152f4_dfc00874ca534e44a4e2166081d76e2b_use");
            }
        });

        Button btnUpdateEmail = findViewById(R.id.btn_retrofit_update_email);
        btnUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateEmail("27bb6c07007e4f178f85c8db1f9152f4_dfc00874ca534e44a4e2166081d76e2b_use", "2431492068@163.com");
            }
        });

        Button btnRegister = findViewById(R.id.btn_retrofit_update_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register() {
        UserBean user = new UserBean();
        user.setUsername("zhangdaxia10");
        user.setPassword("abc123");
        user.setEmail("13141314@qq.com");
        user.setUserType("1");

        Call<ResponseBody> call = service.register(user);
        callEnqueue(call);

    }

    private void callEnqueueUserInfo(Call<ResponseBody> call) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
//                    Log.d(TAG, "onResponse: " + response.body().string());
                    Gson gson = new Gson();
                    Type type = new TypeToken<ResultBase<UserBean>>() {
                    }.getType();
                    ResultBase<UserBean> resultBases = gson.fromJson(response.body().string(), type);
                    UserBean userBean = resultBases.data;
                    String str = String.format("用户名：%s，邮箱：%s，密码：%s，用户类型：%s",
                            userBean.getUsername(), userBean.getEmail(), userBean.getPassword(), userBean.getUserType());
                    Toast.makeText(TestRetrofitActivity.this, str, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Log.e(TAG, "onResponse: " + e.getMessage(), e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
            }
        });
    }

    private void callEnqueue(Call<ResponseBody> call) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG, "onResponse: " + response.body().string());
                } catch (IOException e) {
                    Log.e(TAG, "onResponse: " + e.getMessage(), e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
            }
        });
    }

    private void updateEmail(String authorization, String email) {
        Call<ResponseBody> call = service.updateEmail(authorization, email);
        callEnqueue(call);
    }

    private void getUserinfo(String authorization) {
//        Call<ResponseBody> call = service.getUserinfo(authorization);
//        callEnqueueUserInfo(call);
        Call<ResultBase<UserBean>> call = service.getUserinfo2(authorization);
        call.enqueue(new Callback<ResultBase<UserBean>>() {
            @Override
            public void onResponse(Call<ResultBase<UserBean>> call, Response<ResultBase<UserBean>> response) {
                ResultBase<UserBean> resultBase = response.body();
                if (resultBase!=null&&resultBase.getData()!=null){
                    String str=resultBase.getData().getUsername()+","+resultBase.getData().getEmail();
                    Toast.makeText(TestRetrofitActivity.this, str, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResultBase<UserBean>> call, Throwable t) {

            }
        });
    }


    private void login(String username, String password, String loginCode) {
        Call<ResponseBody> call = service.login(username, password, loginCode);
        callEnqueue(call);
    }

    private void test() {
        Call<ResponseBody> call = service.test();
        callEnqueue(call);
    }

    public void createRertofit() {
        retrofit = new Retrofit.Builder().baseUrl(TestRetrofitService.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(TestRetrofitService.class);
    }
}

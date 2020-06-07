package com.example.hiot_clout.test.networktest;


import com.example.hiot_clout.data.bean.UserBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;


public interface TestRetrofitService {
    //        public static final String baseUrl="http://61.135.169.125/";
    public static final String baseUrl = "http://114.67.88.191:8080/";

    @GET("/")
    Call<ResponseBody> test();

    @POST("/auth/login")
    Call<ResponseBody> login(@Query("username") String username, @Query("password") String password, @Query("loginCode") String loginCode);

    @GET("/user/one")
    Call<ResponseBody> getUserinfo(@Header("Authorization") String authorization);

    @GET("/user/one")
    Call<ResultBase<UserBean>> getUserinfo2(@Header("Authorization") String authorization);

    @PUT("/user/email")
    Call<ResponseBody> updateEmail(@Header("Authorization") String authorization, @Query("email") String email);

    @POST("/user/register")
    Call<ResponseBody> register(@Body UserBean userBean);
}

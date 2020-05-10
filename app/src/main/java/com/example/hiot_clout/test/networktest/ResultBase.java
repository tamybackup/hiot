package com.example.hiot_clout.test.networktest;

import java.io.Serializable;

public class ResultBase<T> implements Serializable {
    /**
     * 返回对象
     */
    T data;

    /**
     * 返回消息
     */
    String msg;

    /**
     * 响应状态
     */
    int status;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}


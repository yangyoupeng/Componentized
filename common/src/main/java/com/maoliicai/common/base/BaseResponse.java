package com.maoliicai.common.base;

/**
 * yangyoupeng  on 2018/4/12.
 */

public class BaseResponse<T> {
    private int code;         //成功 = 200
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}

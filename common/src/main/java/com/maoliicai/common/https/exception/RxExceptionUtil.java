package com.maoliicai.common.https.exception;

import android.net.ParseException;
import android.util.Log;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

/**
 * yangyoupeng  on 2018/4/12.
 * <p>
 * 异常处理类
 */
public class RxExceptionUtil {
    private static final String TAG = "RxExceptionUtil";
    public static String exceptionHandler(Throwable e) {
        String errorMsg = "未知错误";
        if (e instanceof UnknownHostException) {
            errorMsg = "网络不可用";
        } else if (e instanceof SocketTimeoutException) {
            errorMsg = "请求网络超时";
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            errorMsg = convertStatusCode(httpException);

        } else if (e instanceof ParseException || e instanceof JSONException
                || e instanceof com.google.gson.JsonIOException) {
            errorMsg = "数据解析错误";
        }

        return errorMsg;
    }

    private static String convertStatusCode(HttpException httpException){
        Log.d(TAG, "convertStatusCode: "+httpException.code());
        String msg;
        if (httpException.code() >= 500 && httpException.code() < 600) {
            msg = "服务器处理请求出错";
        } else if (httpException.code() >= 400 && httpException.code() < 500) {
            if(httpException.code() == 401){
//                throw new UnLoginException();
                msg = "请重新登录";
            } else {
                msg = "服务器无法处理请求";
            }
        } else if (httpException.code() >= 300 && httpException.code() < 400) {
            msg = "请求被重定向到其他页面";
        } else {
            msg = httpException.message();
        }
        return msg;
    }
}


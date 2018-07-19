package com.maoliicai.common.https.exception;

import android.net.ParseException;
import android.util.Log;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

/**
 * @author mumuji
 * @version 1.0.0
 * @date 2018/5/8
 * @description
 */

public class ExceptionUtil {
    private static final String TAG = "ExceptionUtil";
    private final static int UnknownHostException = 1000;
    private final static int SocketTimeoutException = 2000;
    private final static int JSONException = 3000;
    private final static int ServiceException = 500;
    private final static int CustomerException = 400;
    private final static int RedirectException = 300;
    private final static int NoLoginException = 401;

    public static int exceptionHandler(Throwable e) {
        int code = 0;
        if (e instanceof UnknownHostException) {
            code = UnknownHostException;
        } else if (e instanceof SocketTimeoutException) {
            code = SocketTimeoutException;
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            code = convertCode(httpException);
        } else if (e instanceof ParseException || e instanceof JSONException
                || e instanceof com.google.gson.JsonIOException) {
            code = JSONException;
        }
        return code;
    }

    private static int convertCode(HttpException httpException){
        int code;
        if (httpException.code() >= 500 && httpException.code() < 600) {
            code = ServiceException;
        } else if (httpException.code() >= 400 && httpException.code() < 500) {
            if(httpException.code() == 401){
                code = NoLoginException;
            } else {
                code = CustomerException;
            }
        } else if (httpException.code() >= 300 && httpException.code() < 400) {
            code = RedirectException;
        } else {
            code = httpException.code();
        }

        return code;
    }

    public static String getMsg(int code){
        String errorMsg = "网络不给力";
        switch (code){
            case UnknownHostException:
                errorMsg = "网络不给力";
                break;
            case SocketTimeoutException:
                errorMsg = "请求网络超时";
                break;
            case JSONException:
                errorMsg = "数据解析错误";
                break;
            case ServiceException:
                errorMsg = "服务器处理请求出错";
                break;
            case CustomerException:
                errorMsg = "服务器无法处理请求";
                break;
            case RedirectException:
                errorMsg = "请求被重定向到其他页面";
                break;
            case NoLoginException:
                errorMsg = "请重新登录";
                break;
        }
        return errorMsg;
    }

}

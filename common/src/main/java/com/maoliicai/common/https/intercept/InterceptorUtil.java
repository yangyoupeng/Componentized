package com.maoliicai.common.https.intercept;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.maoliicai.common.BuildConfig;
import com.maoliicai.common.base.BaseApplication;
import com.maoliicai.common.https.public_parameters.HeadersPublic;
import com.maoliicai.common.utils.AppUtils;
import com.maoliicai.common.utils.MD5Util;
import com.maoliicai.common.utils.SP;
import com.maoliicai.common.utils.SPUtils;
import com.orhanobut.logger.Logger;

import java.io.EOFException;
import java.io.IOException;
import java.net.NetworkInterface;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;

/**
 * yangyoupeng  on 2018/4/12.
 * <p>
 * 日志拦截器
 */

public class InterceptorUtil {
    private static String TAG = "----";
    private static final String SPLIT_STR_THIRD = "$";

    private static final Charset UTF8 = Charset.forName("UTF-8");

    private Map<String, String> mHeadParamMap = new LinkedHashMap<>();

    //日志拦截器
    public static HttpLoggingInterceptor LogInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.w(TAG, "log: " + message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);//设置打印数据的级别
    }

    /**
     * 在这里做一些想做的事,比如token失效时,重新获取token或者添加header等等,
     *
     * @return
     */
    public Interceptor HeaderInterceptor(Context context) {

        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //重新处理请求
                Request newRequest = handleRequest(chain.request(), context);
                //执行请求
                Response response = chain.proceed(newRequest);
                //打印请求url和结果
                logResponse(response);
                return response;
            }
        };
    }

    /**
     * 添加header参数
     *
     * @param request
     * @return
     */
    private HttpUrl url;

    private Request handleRequest(Request request, Context context) throws IOException {
        Request.Builder requestBuilder = request.newBuilder();
        url = request.url();
        getParams(request);
        //添加头信息，并获取请求参数加密
        handleHeaders(requestBuilder, context);
        Request build = requestBuilder.build();
        //构建完成
        return build;
    }

    /**
     * 处理请求头
     * app包名：appPackageName
     * app版本号：version
     * 请求方法版本号：method_version   接口无多个版本方法，可以为空
     * 渠道名称：channel
     * 渠道签名：channelSigna   与旧版app保持一致
     * 签名：imeiSigna          与旧版app保持一致
     * 登录标识：token			 未登录情况可以为空
     * 请求签名：sign
     * 时间戳：timestamp
     * 设备编号：deviceId
     * 设备ID签名：deviceIdSigna与旧版app保持一致
     * 设备标识：deviceToken
     * 设备标识签名：deviceTokenSigna与旧版app保持一致
     * 移动设备类型：deviceType    1.安卓  2.IOS
     * <p>
     * //     * @param builder
     */
    private void handleHeaders(Request.Builder request, Context context) {
        String deviceId = AppUtils.getDeviceId(context);
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = "";
        }
        String timestamp = String.valueOf(System.currentTimeMillis());

        request.addHeader(HeadersPublic.APP_PACKAGE_NAME, BaseApplication.getInstance().getPackageName());
        request.addHeader(HeadersPublic.VERSION, String.valueOf(BuildConfig.VERSION_CODE));
        request.addHeader(HeadersPublic.METHOD_VERSION, HeadersPublic.METHOD_VERSION_CODE);//请求方法版本号,主要是用来适配版本，怕以后接口改变，传相应的版本号
        request.addHeader(HeadersPublic.CHANNEL, SP.getChannel(context));
        request.addHeader(HeadersPublic.CHANNEL_SIGNA, getSigna(SP.getChannel(context)));
        request.addHeader(HeadersPublic.TOKEN, String.valueOf(SPUtils.get(context, HeadersPublic.TOKEN, "")));
        request.addHeader(HeadersPublic.SIGN, signWithList(timestamp));//加密之后
        request.addHeader(HeadersPublic.TIMESTAMP, timestamp);
        request.addHeader(HeadersPublic.DEVICE_ID, deviceId);
        request.addHeader(HeadersPublic.DEVICE_ID_SIGNA, getSigna(deviceId));
//        request.addHeader(HeadersPublic.DEVICE_TOKEN, deviceToken);
//        request.addHeader(HeadersPublic.DEVICE_TOKEN_SIGNA, getSigna(deviceToken));
        request.addHeader(HeadersPublic.DEVICE_TYPE, HeadersPublic.TYPE);

    }

    /**
     * 把获取的参数添加到map集合
     *
     * @param builder
     * @return
     */
    private void getParams(Request builder) throws IOException {
        Map<String, String> getParam = new LinkedHashMap<>();
        String jsonBody = "";
        Buffer buffer = new Buffer();
        builder.body().writeTo(buffer);
        Charset charset = UTF8;
        MediaType contentType = builder.body().contentType();
        if (contentType != null) {
            charset = contentType.charset(UTF8);
        }
        if (isPlaintext(buffer)) {
            jsonBody = buffer.readString(charset);
            Log.e("----", "jsonBody:" + jsonBody);
        }
        if (!TextUtils.isEmpty(jsonBody)) {
            getParam = new Gson().fromJson(jsonBody, new TypeToken<HashMap<String, Object>>() {
            }.getType());
        }
        mHeadParamMap = getParam;
    }


    /**
     * signa签名生成规则
     */
    private String signWithList(String timestamp) {
        mHeadParamMap.put("timestamp", timestamp);
        ArrayList<String> list = new ArrayList<>(mHeadParamMap.keySet());
        Collections.sort(list);
        List<String> values = new ArrayList<>();

        String s = String.valueOf(url);
        Log.e(TAG, "完整的url: " + s);
        int start = s.indexOf("/api");
        String substring = s.substring(start, s.length());
        Log.e(TAG, "截取之后的url: " + substring);

        values.add(substring);
        for (String key : list) {
            if (key.equals("sign")) {
                continue;
            }
            Object mapValue = mHeadParamMap.get(key);
            if(mapValue instanceof LinkedTreeMap){
                String v = new Gson().toJson(mapValue);
                values.add(v);
            } else {
                values.add(mHeadParamMap.get(key));
            }
        }
        StringBuffer sb = new StringBuffer(HeadersPublic.APP_KEY);
        int size = values.size();
        for (int i = 0; i < size; i++) {
            Object o = values.get(i);
            sb.append(o.toString());
            if (i < size - 1) {
                sb.append(SPLIT_STR_THIRD);
            }
        }
        sb.append(HeadersPublic.APP_SECRET);
        Log.e(TAG, "-------拼接之后的字符串：" + sb);
        return MD5Util.MD5Encode(sb.toString(), "UTF-8");
    }


    /**
     * 直接读取body().toString()的话，会消耗掉响应流，这里手动复制响应流
     */
    private void logResponse(Response response) throws IOException {
        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        if (!bodyEncoded(response.headers())) {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(UTF8);
                } catch (UnsupportedCharsetException e) {
                    e.printStackTrace();
                    return;
                }
            }
            if (!isPlaintext(buffer)) {
                return;
            }
            if (contentLength != 0) {
                String result = buffer.clone().readString(charset);
                Logger.e(TAG, "---------新API，请求结果----------------------");
                Logger.e(TAG, "response.body():" + result);
                Logger.e(TAG, "----------------------------------------------");
            }
        }
    }

    static boolean isPlaintext(Buffer buffer) throws EOFException {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }


    private static boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }

    /**
     * 一般接口调用-signa签名生成规则
     *
     * @param ts 时间戳
     */
    public static String getSigna(String ts) {
        // appsecrt拼接ts的字符串后进行MD5（32）
        String signa = MD5Util.MD5Encode(HeadersPublic.APP_SECRET + ts, "UTF-8");
        // 得到的MD5串拼接appkey再次MD5，所得结果转大写
        signa = MD5Util.MD5Encode(signa + HeadersPublic.APP_KEY, "UTF-8").toUpperCase();
        return signa;
    }
}
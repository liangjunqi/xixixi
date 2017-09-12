package com.jiyun.day01_demo02;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by lenovo on 2017/9/11.
 */

public class Utils {
    private static Utils utils;
    private OkHttpClient okHttpClient;
    private  Utils(){
        okHttpClient = new OkHttpClient();
    }
    public static synchronized Utils getInstance(){
        if (utils==null){
            utils = new Utils();
        }
        return utils;
    }
    public   void sendGet(String utl, Callback callback){
        Request build = new Request.Builder().url(utl).build();
        Call call = okHttpClient.newCall(build);
        call.enqueue(callback);
    }
}
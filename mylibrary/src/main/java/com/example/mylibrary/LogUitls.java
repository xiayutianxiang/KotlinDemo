package com.example.mylibrary;

class LogUtils {

    public LogUtils(){}

    public static void d(String msg){
        if(msg!=null && msg.length()>0){
            android.util.Log.d("zsp",msg);
        }
    }
}

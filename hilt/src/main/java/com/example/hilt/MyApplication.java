package com.example.hilt;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import dagger.hilt.android.HiltAndroidApp;

/**
 * Created  by Administrator on 2021/6/30
 * 实现依赖注入第一步
 * 1、@HiltAndroidApp生成相应的组件
 */
@HiltAndroidApp
public class MyApplication extends Application {

    @SuppressLint("LongLogTag")
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MyApplication super Class= ",this.getClass().getSuperclass().getSimpleName());
    }
}

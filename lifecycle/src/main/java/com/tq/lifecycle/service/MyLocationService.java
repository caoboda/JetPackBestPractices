package com.tq.lifecycle.service;

import android.util.Log;
import androidx.lifecycle.LifecycleService;

/**
 * Created by cbd on 2021/6/17 18:15
 * 使用LifeCycle解绑Service组件
 */
public class MyLocationService extends LifecycleService {

    public MyLocationService() {
        Log.e("gps= ","MyLocationService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("gps= ","onCreate");
        MyLocationObserver myLocationObserver=new MyLocationObserver(this);
        getLifecycle().addObserver(myLocationObserver);
    }
}

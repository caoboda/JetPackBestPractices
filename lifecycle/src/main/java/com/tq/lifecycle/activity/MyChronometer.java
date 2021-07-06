package com.tq.lifecycle.activity;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Chronometer;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Created by cbd on 2021/6/17 17:21
 * 使用LifeCycle解耦系统组件（MainActivity）和普通的组件（Chronometer）
 */
public class MyChronometer extends Chronometer implements LifecycleObserver {

    private long elapsedRealtime;

    public MyChronometer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //通过注解 使得方法的执行和Activity的生命周期相关联
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void startMyChronometer(){
        Log.e("MyChronometer","ON_RESUME");
        setBase(SystemClock.elapsedRealtime()-elapsedRealtime);
        start();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void stopMyChronometer(){
        Log.e("MyChronometer","ON_PAUSE");
        elapsedRealtime=SystemClock.elapsedRealtime()-getBase();
        stop();
    }
}

package com.tq.lifecycle;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Created  by Administrator on 2021/6/17
 * LifeCycle好处：
 * 1、帮助开发者建立可感知生命周期的组件
 * 2、组件在其内部管理自己的生命周期，从而降低模块耦合度
 * 3、降低内存泄漏发生的可能性
 * 4、Activity、Fragment、Sevice、Application都有自己的LifeCycle支持
 *
 */
public class ApplicationObserver implements LifecycleObserver {
    private String TAG="ApplicationObserver";

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void  onCreate(){
        Log.e(TAG,"Lifecycle.Event.ON_CREATE");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void  onStart(){
        Log.e(TAG,"Lifecycle.Event.ON_START");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void  onResume(){
        Log.e(TAG,"Lifecycle.Event.ON_RESUME");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void  onPause(){
        Log.e(TAG,"Lifecycle.Event.ON_PAUSE");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void  onStop(){
        Log.e(TAG,"Lifecycle.Event.ON_STOP");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void  onDestroy(){
        Log.e(TAG,"Lifecycle.Event.ON_DESTROY");
    }
}

package com.tq.lifecycle;

import android.app.Application;

import androidx.lifecycle.ProcessLifecycleOwner;

/**
 * Created  by Administrator on 2021/6/17
 * //使用ProcessLifecycleOwner监听应用程序生命周期
 * //ProcessLifecycleOwner 针对整个应用程序的监听 和Activity数量无关
 *                         Lifecycle.Event.ON_CREATE 只会被调用一次
 *                         Lifecycle.Event.ON_DESTROY永远不会被调用
 *                         退到后台  Lifecycle.Event.ON_PAUSE->Lifecycle.Event.ON_STOP
 *                         回到前台  Lifecycle.Event.ON_START->Lifecycle.Event.ON_RESUME
 */
public class MyApplication  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ProcessLifecycleOwner.get().getLifecycle().addObserver(new ApplicationObserver());
    }
}

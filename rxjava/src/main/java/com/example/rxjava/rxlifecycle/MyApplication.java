package com.example.rxjava.rxlifecycle;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Created by cbd on 2021/7/8
 *   处理单个观察者 的disposable.dispose();报下面错误问题(一般使用CompositeDisposable处理添加多个disposable处理多个)
 *   io.reactivex.exceptions.UndeliverableException: The exception could not be delivered to the consumer because it has already canceled/disposed the flow or the exception has nowhere to go to begin with. Further reading: https://github.com/ReactiveX/RxJava/wiki/What's-different-in-2.0#error-handling | java.lang.InterruptedException
 */
public class MyApplication extends Application {
    public   final   String TAG=this.getClass().getSimpleName();


    @Override
    public void onCreate() {
        super.onCreate();
        //取消订阅后，抛出的上述异常无法处理，导致闪退
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG, "MyApplication setErrorHandler subscribe==== " +throwable.getMessage());

            }
        });
    }
}

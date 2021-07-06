package com.example.rxjava;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created  by Administrator on 2021/7/3
 * subscribeOn线程切换
 */
public class ThreadSwitchActivity extends AppCompatActivity {
    public   final   String TAG=this.getClass().getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testThreadSwitch();
    }

    private void testThreadSwitch() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NotNull ObservableEmitter<Object> emitter) throws Exception {
                //发射事件
                Log.e(TAG, "subscribe " +Thread.currentThread());
                //模拟网络请求 耗时操作
                Thread.sleep(3000);
                emitter.onNext(111);
                emitter.onNext(22222);
                emitter.onNext(333333);
                emitter.onComplete();
               }
               }).subscribeOn(Schedulers.io())//决定上游subscribe方法所处的线程(即产生事件发送事件所处的线程)
                .observeOn(AndroidSchedulers.mainThread())//决定下游事件被处理时所处的线程
                .map(new Function<Object, Object>() {
                    @Override
                    public Object apply(@NotNull Object o) throws Exception {
                        Log.e(TAG, "apply " +Thread.currentThread());
                        return "000";
                    }
                })
                .observeOn(Schedulers.io())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {
                        Log.e(TAG, "onSubscribe " +Thread.currentThread());
                    }

                    @Override
                    public void onNext(@NotNull Object o) {
                        Log.e(TAG, "onNext " +Thread.currentThread()+" o= "+o);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Log.e(TAG, "onError " +Thread.currentThread());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete" +Thread.currentThread());
                    }
                });
    }
}

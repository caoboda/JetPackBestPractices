package com.example.rxjava.rxlifecycle;

import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import org.jetbrains.annotations.NotNull;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
/**
 * Created by cbd on 2021/7/8
 * 手写LifeCycle,自定义生命周期管理
 * 实现LifecycleObserver感知生命周期，并回调
 */
public class RxLifeCycle<T> implements LifecycleObserver, ObservableTransformer<T,T> {

    CompositeDisposable compositeDisposable=new CompositeDisposable();


    //观察者回调
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(){
        Log.e("RxLifeCycle=","onDestroy");
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }

    @Override
    public ObservableSource<T> apply(@NotNull Observable<T> upstream) {
        //doOnSubscribe每个被观察者每次订阅之前都会执行，而每个观察者都会实现Disposable
        //每个观察者的disposable添加到compositeDisposable中，一并取消，所以观察者回调不会执行
        return upstream.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                compositeDisposable.add(disposable);
            }
        });
    }

    @NotNull
    public static <T> RxLifeCycle<T> bindRxLifeCycle(@NotNull LifecycleOwner lifecycleOwner){
        RxLifeCycle<T> rxLifeCycle=new RxLifeCycle<>();
        lifecycleOwner.getLifecycle().addObserver(rxLifeCycle);
        return rxLifeCycle;
    }
}

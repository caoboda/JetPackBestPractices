package com.example.rxjava.source.create;

/**
 * Created  by Administrator on 2021/7/3
 * RxJava是一种变种的观察者模式(它的事件和被观察者进行解耦)，所以需要一个发射器去发射事件
 * 事件发射器
 * 事件发射器Emitter需要和被观察者建立关联用到接口ObservableOnSubscribe
 */
public interface Emitter<T> {

    void onNext(T t);

    void onError(Throwable throwable);

    void onComplete();
}

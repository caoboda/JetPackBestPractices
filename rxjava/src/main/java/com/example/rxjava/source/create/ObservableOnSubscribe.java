package com.example.rxjava.source.create;

/**
 * Created  by Administrator on 2021/7/3
 * 事件发射器Emitter和被观察者建立关联
 */
public interface ObservableOnSubscribe<T> {
    //这里也用subscribe是因为
    // 1、被观察者和（Emitter发送的事件）发送事件进行解耦
    // 2、Emitter发射器会持有一个我们观察者的引用，我们可以把它看成事件的桥梁
    void subscribe(Emitter<T> emitter);
}

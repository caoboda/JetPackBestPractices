package com.example.rxjava.source.create;

/**
 * Created  by Administrator on 2021/7/3
 * 被观察者顶层接口
 */
public interface ObservableSource<T> {
    /**
     * 和观察者建立订阅关系 相当于addObserver添加观察者到我们的被观察者
     */
    void subscribe(Observer observer);
}

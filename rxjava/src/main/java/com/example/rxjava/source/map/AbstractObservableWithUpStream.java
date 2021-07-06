package com.example.rxjava.source.map;

import com.example.rxjava.source.create.Observable;
import com.example.rxjava.source.create.ObservableSource;

/**
 * Created  by Administrator on 2021/7/4
 * 抽象装饰类(装饰器模式)
 * 所有的扩展类都继承它,相当于对ObservableSource<T> source被观察者的包装处理
 *
 */
public abstract class AbstractObservableWithUpStream<T,U> extends Observable<U> {

    protected final ObservableSource<T> source;

    protected AbstractObservableWithUpStream(ObservableSource<T> source) {
        this.source = source;
    }
}

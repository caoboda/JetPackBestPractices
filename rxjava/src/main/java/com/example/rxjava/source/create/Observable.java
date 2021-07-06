package com.example.rxjava.source.create;

import com.example.rxjava.source.map.Function;
import com.example.rxjava.source.map.ObservableFlatMap;
import com.example.rxjava.source.map.ObservableMap;
import com.example.rxjava.source.scheduler.ObservableSubscribeOn;
import com.example.rxjava.source.scheduler.Scheduler;

/**
 * Created  by Administrator on 2021/7/3
 * 被观察者的核心抽象类
 */
public abstract class Observable<T> implements ObservableSource<T>{

    @Override
    public void subscribe(Observer observer) {
        //这里和谁(哪个观察者)建立订阅
        //怎么简历订阅
        //交给自己的开发人员去实现 需要提供一个抽象方法
        subscribeActual(observer);
    }

   protected abstract void  subscribeActual(Observer<T> observer);

    //创建create操作符
    public static <T> Observable<T> create(ObservableOnSubscribe<T> source){
        return  new ObservableCreate<>(source);
    }

    //创建map操作符
    //这里不是静态方法，是基于当前的被观察者来调用
    public <R> ObservableMap<T,R> map(Function<T,R> function){
        return  new ObservableMap<>(this,function);
    }

    //创建flatMap操作符
    //这里不是静态方法，是基于当前的被观察者来调用
    public <R> ObservableFlatMap<T,R> flatMap(Function<T,ObservableSource<R>> function){
        return  new ObservableFlatMap<>(this,function);
    }

    //创建subscribeOn操作符
    //这里不是静态方法，是基于当前的被观察者来调用
    public  ObservableSubscribeOn<T> subscribeOn(Scheduler scheduler){
        return  new ObservableSubscribeOn<>(this,scheduler);
    }
}

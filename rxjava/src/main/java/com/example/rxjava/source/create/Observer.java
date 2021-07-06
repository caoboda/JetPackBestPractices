package com.example.rxjava.source.create;

/**
 * Created  by Administrator on 2021/7/3
 * 观察者
 */
public interface Observer<T> {
    //和被观察者建立订阅关系的回调
   void  onSubscribe();

   void onNext(T t);

   void onError(Throwable throwable);

   void onComplete();
}

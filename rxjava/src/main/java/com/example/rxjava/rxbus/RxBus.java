package com.example.rxjava.rxbus;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
/**
 * Created by cbd on 2021/7/8
 */
public class RxBus {
    private final Subject<Object> mBus;

    private RxBus(){
        //线程安全
        mBus= PublishSubject.create().toSerialized();
    }

    private static class Holder{
        private static final RxBus BUS=new RxBus();
    }


    public static RxBus get() {
        return Holder.BUS;
    }

    public void post(Object event){
        mBus.onNext(event);
    }

    public <T> Observable<T> toObservable(Class<T> tClass){
        return mBus.ofType(tClass);
    }
}

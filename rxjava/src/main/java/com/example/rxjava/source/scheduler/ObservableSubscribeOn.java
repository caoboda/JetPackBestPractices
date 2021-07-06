package com.example.rxjava.source.scheduler;

import com.example.rxjava.source.create.ObservableSource;
import com.example.rxjava.source.create.Observer;
import com.example.rxjava.source.map.AbstractObservableWithUpStream;

/**
 * Created  by Administrator on 2021/7/5
 */
public class ObservableSubscribeOn<T> extends AbstractObservableWithUpStream<T,T> {
    Scheduler scheduler;

    public ObservableSubscribeOn(ObservableSource<T> source, Scheduler scheduler) {
        super(source);
        this.scheduler = scheduler;
    }

    @Override
    protected void subscribeActual(Observer observer) {
        Scheduler.Worker worker=scheduler.createWorke();
        worker.scheduler(new SubscribeTask(new SubScribeOnObserver<>(observer)));
    }


    class SubScribeOnObserver<T> implements Observer<T>{
        Observer<T> downStream;

        public SubScribeOnObserver(Observer<T> downStream) {
            this.downStream = downStream;
        }

        @Override
        public void onSubscribe() {

        }

        @Override
        public void onNext(T t) {
            downStream.onNext(t);
        }

        @Override
        public void onError(Throwable throwable) {
            downStream.onError(throwable);
        }

        @Override
        public void onComplete() {
            downStream.onComplete();
        }
    }

    final class  SubscribeTask implements Runnable{

        final SubScribeOnObserver parent;

        SubscribeTask(SubScribeOnObserver<T> parent) {
            this.parent = parent;
        }

        @Override
        public void run() {
            source.subscribe(parent);
        }
    }
}

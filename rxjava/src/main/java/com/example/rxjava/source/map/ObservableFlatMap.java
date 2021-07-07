package com.example.rxjava.source.map;

import com.example.rxjava.source.create.ObservableSource;
import com.example.rxjava.source.create.Observer;

/**
 * Created  by Administrator on 2021/7/4
 */
public class ObservableFlatMap<T,U> extends AbstractObservableWithUpStream<T,U>{

    Function<T, ObservableSource<U>> function;

    public ObservableFlatMap(ObservableSource<T> source, Function<T, ObservableSource<U>> function) {
        super(source);
        this.function = function;
    }

    @Override
    protected void subscribeActual(Observer<U> observer) {
        source.subscribe(new FlatMapObserver(observer,function));
    }

    static class FlatMapObserver<T,U> implements Observer<T>{
        final Observer<U> downStream;
        final Function<T,ObservableSource<U>> mapper;

        FlatMapObserver(Observer<U> downStream, Function<T, ObservableSource<U>> mapper) {
            this.downStream = downStream;
            this.mapper = mapper;
        }

        @Override
        public void onSubscribe() {
            downStream.onSubscribe();
        }

        @Override
        public void onNext(T t) {
            ObservableSource<U> observable = mapper.apply(t);
            observable.subscribe(new Observer<U>() {
                @Override
                public void onSubscribe() {

                }

                @Override
                public void onNext(U u) {
                    downStream.onNext(u);
                }

                @Override
                public void onError(Throwable throwable) {

                }

                @Override
                public void onComplete() {

                }
            });
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
}

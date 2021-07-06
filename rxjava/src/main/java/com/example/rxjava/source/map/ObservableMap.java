package com.example.rxjava.source.map;

import com.example.rxjava.source.create.ObservableSource;
import com.example.rxjava.source.create.Observer;

/**
 * Created  by Administrator on 2021/7/4
 */
public class ObservableMap<T,U> extends AbstractObservableWithUpStream<T,U>{

    Function<T,U> function;

    public ObservableMap(ObservableSource<T> source, Function<T, U> function) {
        super(source);
        this.function = function;
    }

    @Override
    protected void subscribeActual(Observer<U> observer) {
        source.subscribe(new MapObserver(observer,function));
    }

    static class MapObserver<T,U> implements Observer<T>{
        final Observer<U> downStream;
        final Function<T,U> mapper;

        MapObserver(Observer<U> downStream, Function<T, U> mapper) {
            this.downStream = downStream;
            this.mapper = mapper;
        }

        @Override
        public void onSubscribe() {
            downStream.onSubscribe();
        }

        @Override
        public void onNext(T t) {
           U u= mapper.apply(t);
           downStream.onNext(u);
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

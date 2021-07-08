package com.example.rxjava.source.scheduler;

import com.example.rxjava.source.create.ObservableSource;
import com.example.rxjava.source.create.Observer;
import com.example.rxjava.source.map.AbstractObservableWithUpStream;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created  by Administrator on 2021/7/8
 */
public class ObserableObserverOn<T> extends AbstractObservableWithUpStream<T,T> {
    final  Scheduler scheduler;

    public ObserableObserverOn(ObservableSource<T> source, Scheduler scheduler) {
        super(source);
        this.scheduler = scheduler;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        Scheduler.Worker worker=scheduler.createWorke();
        source.subscribe(new ObserverOnObserver(observer,worker));
    }

    static final class ObserverOnObserver<T> implements Observer<T>,Runnable{
        final Observer<T> downStream;
        final Scheduler.Worker worker;
        final Deque<T> deque;
        volatile boolean done;
        volatile boolean over;
        volatile Throwable error;

        public ObserverOnObserver(Observer<T> downStream, Scheduler.Worker worker) {
            this.downStream = downStream;
            this.worker = worker;
            deque=new ArrayDeque<>();
        }

        @Override
        public void onSubscribe() {
            downStream.onSubscribe();
        }

        @Override
        public void onNext(T t) {
            //把事件加入队列，offer不抛异常，只返回fasle
            deque.offer(t);
            schedule();
        }

        private void schedule() {
            worker.scheduler(this);
        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onComplete() {

        }

        @Override
        public void run() {
           drainNormal();
        }

        //从队列中取出事件并处理
        private void drainNormal() {
            final  Deque<T> q=deque;
            final  Observer<T> a=downStream;

            while (true){
                boolean d=done;
                //取出数据，没有数据的时候不会抛异常,返回null
                T t=q.poll();
                boolean empty=t==null;
                if(checkTerminated(d,empty,a)){
                   return;
                }
                if(empty){
                    break;
                }
                a.onNext(t);
            }
        }

        private boolean checkTerminated(boolean d, boolean empty, Observer<T> a) {
            if(over){
                deque.clear();
                return true;
            }
            if(d){
                Throwable e=error;
                if(e!=null){
                    over=true;
                    a.onError(error);
                    return true;
                }else if(empty){
                    over=true;
                    a.onComplete();
                    return true;
                }
            }
           return  false;
        }
    }
}

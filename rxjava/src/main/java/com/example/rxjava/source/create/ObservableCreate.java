package com.example.rxjava.source.create;

/**
 * Created  by Administrator on 2021/7/4
 */
public class ObservableCreate<T> extends Observable<T> {
   final ObservableOnSubscribe source;

    public ObservableCreate(ObservableOnSubscribe source) {
        this.source = source;
    }

    @Override
    protected void subscribeActual(Observer observer) {
        //被观察者和观察者建立关系后最先调用这个方法
        observer.onSubscribe();
        //这里建立观察者、被观者者、事件发射器之间的关系
        //1、建立观察者、事件发射器之间的关系
        CreateEmitter<T> createEmitter=new CreateEmitter<T>(observer);
        //2、建立被观者者、事件发射器之间的关系(这里通过构造方法传入ObservableOnSubscribe对象，调用subscribe建立和发射器之间的联系)
        source.subscribe(createEmitter);

    }

    static class CreateEmitter<T> implements  Emitter<T>{

        //我们的被观察者通过Emitter发送的事件 在下面3个方法调用观察者的相应的3个方法通知观察者
        //所以这里Emitter发射器会持有一个我们观察者的引用，我们可以把它看成事件的桥梁
        Observer observer;
        //这里保证onError或者onError只能执行一个，并且只要执行了其中一个下面3个方法都不会再调用
        boolean isDone;

        public CreateEmitter(Observer observer) {
            this.observer = observer;
        }

        @Override
        public void onNext(T t) {
            if(isDone){
                return;
            }
            observer.onNext(t);
        }

        @Override
        public void onError(Throwable throwable) {
            if(isDone){
                return;
            }
            observer.onError(throwable);
            isDone=true;
        }

        @Override
        public void onComplete() {
            if(isDone){
                return;
            }
            observer.onComplete();
            isDone=true;
        }
    }
}

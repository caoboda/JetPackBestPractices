package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * RxJava：响应式编程框架,是由微软提出的一个综合异步和基于事件驱动编程的库, LiveData也是一种JetPack提供的一种响应式编程组件
 * RxJava的核心就是异步数据流和响应式编程：
 * 1、把所有的事件（数据）看成一条河流，它可以被观察，过滤或者操作，也可以和另外一条河流汇合成一条新的河流。
 * 2、一旦事件产生或者发生变化，就可以触发观察这些事件的角色(观察者)，做出响应处理。
 * RxJava优势及适用场景
 * RxJava优势
 * 1、具备响应式编程该有的特性。
 * 2、为异步而生，无需手动创建线程，具备线程切换的能力。
 * 3、支持链式调用，保证代码的简洁性。
 * 4、拥有各种操作符，功能强大，可以满足各种业务需求。
 * 5、可以简化异常的处理
 * RxJava适用场景
 * 网络请求、数据读的读写、文件读写、定时任务等各种耗时操作需要通过异步来完成的操作都可以使用RxJava来完成
 * RxJava重要概念
 * 观察者：Observer，观察事件的变化并处理的主要角色。Comsumer也可以看成一种特殊的观察者。
 * 被观察者：触发事件并决定什么时候发送事件的主要角色。注意：异常和完成也是一种事件，主要有下面几种被观察者：
 * 1、Observable、Flowable、Single、Completable、Maybe都是被观察者。
 * 2、Flowable是支持背压的一种被观察者。。
 * 3、Single、Completable、Maybe是一种简化版的Observable。
 * 4、几种观察者通过toObservable/toFlowable/toSingle/toCompletable/toMaybe相互转换。
 * 订阅：subscribe,把被观察者和观察者简历关联。
 */
public class CreateOperatorActivity extends AppCompatActivity {
    private final  String TAG=this.getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建操作符
       // testCreateOperator();
        //testjust();
        testFromArray();
    }


    @SuppressLint("CheckResult")
    private void testCreateOperator(){
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@SuppressLint("CheckResult") @NotNull ObservableEmitter<Object> emitter) throws Exception {
                //数据或者事件的处理
                // 发送事件
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onNext("3");
                //下面两个操作互斥只执行其中一个
                //报错
               // emitter.onComplete();
                emitter.onError(new Throwable("抛个异常"));
                //不报错
               // emitter.onError(new Throwable("抛个异常"));
              //  emitter.onComplete();
                //耗时操作
                //网络操作
                //异步操作都在这里处理

            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.e(TAG,"accept "+o);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                //在这里接收异常  简化异常的处理
                // Log.e(TAG,"accept "+throwable);
            }
        });
                //.subscribe(observer);
    }



    private void testjust(){
        //just操作符 方便快速的创建Observable 他有10个构造方法 ，最多只能传递10个参数
        Observable.just(1,"xx","sss",33) .subscribe(observer);
    }
    @SuppressLint("CheckResult")
    private void testFromArray(){
        //fromArray操作符（just就是调用的fromArray操作符实现的） 可以传无数个
      //  Observable.fromArray(1,"地方是多少","大",111,3423,234,34,43,435,544,54,545,545,541114).subscribe(observer);
       /* List<Object> list=new ArrayList<>();
        list.add("1");
        list.add(2);
        list.add(3);
        Observable.fromIterable(list).subscribe(observer);*/
        //拿到返回的值 拿到有些事件（还没执行完）的状态
     /*   Observable.fromFuture(new Future<Object>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public Object get() throws ExecutionException, InterruptedException {
                return "result";
            }

            @Override
            public Object get(long timeout, TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
                return null;
            }
        }).subscribe(observer);*/
        //回调值 如我们的网络请求结果通过fromCallable回调出去
        Observable.fromCallable(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return "callable result";
            }
        }).subscribe(observer);
    }

    Observer<Object> observer=new Observer<Object>() {
        @Override
        public void onSubscribe(@NotNull Disposable d) {
            Log.e(TAG,"onSubscribe "+d.isDisposed());
        }

        @Override
        public void onNext(@NotNull Object o) {
            Log.e(TAG,"onNext "+o);
        }

        @Override
        public void onError(@NotNull Throwable e) {
            Log.e(TAG,"onError "+e.toString());

        }

        @Override
        public void onComplete() {
            Log.e(TAG,"onComplete ");
        }
    };
}
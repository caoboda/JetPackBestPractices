package com.example.rxjava.source;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.rxjava.R;
import com.example.rxjava.source.create.Emitter;
import com.example.rxjava.source.create.Observable;
import com.example.rxjava.source.create.ObservableOnSubscribe;
import com.example.rxjava.source.create.ObservableSource;
import com.example.rxjava.source.create.Observer;
import com.example.rxjava.source.map.Function;
import com.example.rxjava.source.scheduler.Schedulers;

/**
 * 手写RxJava核心代码 create() map() flatMap()
 * 1、RxJava用到了变种的观察者模式和装饰器模式
 */
public class TestMyRxJavaActivity extends AppCompatActivity {
    public final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_my_rx_java);
        testDiyCreate();
    }

    //手写create操作符
    private void testDiyCreate() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(Emitter<Object> emitter) {
                Log.e(TAG, "subscribe " + Thread.currentThread());
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onNext("3");
                //下面两个操作互斥只执行其中一个
                //报错
                emitter.onComplete();
                emitter.onError(new Throwable("抛个异常"));

            }
        })/*.map(new Function<Object, Object>() {
            @Override
            public String apply(Object o) {
                return "map类型转换后的" + o;
            }
        })*/
                .flatMap(new Function<Object, ObservableSource<Object>>() {
                    @Override
                    public ObservableSource<Object> apply(Object s) {
                        return Observable.create(new ObservableOnSubscribe<Object>() {
                            @Override
                            public void subscribe(Emitter<Object> emitter) {
                                emitter.onNext("flatMap的处理过后再发送的 " + s + Thread.currentThread());
                            }
                        });
                    }
                }).subscribeOn(Schedulers.newThread())
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe() {
                        Log.e(TAG, "onSubscribe " + Thread.currentThread());
                    }

                    @Override
                    public void onNext(Object o) {
                        Log.e(TAG, "onNext " + o + Thread.currentThread());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.e(TAG, "onError " + throwable.toString() + Thread.currentThread());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete " + Thread.currentThread());
                    }
                });


    }
}
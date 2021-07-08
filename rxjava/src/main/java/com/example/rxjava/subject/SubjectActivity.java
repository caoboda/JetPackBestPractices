package com.example.rxjava.subject;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import com.example.rxjava.R;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;
/**
 * Subject同时代表一个被观察者和观察者，允许事件从单个源多播到多个观察者
 * RxJava提供四种Subject
 * 用处：可用来实现RxBus,类似EventBus和LiveDataBus
 */

public class SubjectActivity extends AppCompatActivity {
    public   final   String TAG=this.getClass().getSimpleName();

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        //AsyncSubject只发射最后一个事件，无论是在订阅前还是订阅后,且必须调用onComplete()
        AsyncSubject<Object> asyncSubject=AsyncSubject.create();
        asyncSubject.onNext("A");
        asyncSubject.onNext("B");
        asyncSubject.subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.e(TAG,"asyncSubject accept= "+o);
            }
        });
        asyncSubject.onNext("C");
        asyncSubject.onComplete();

        //AsyncSubject只发射订阅之前最后一个事件和订阅之后的所有事件,不须调用onComplete()
        Log.e(TAG,"============================");
        BehaviorSubject<Object> behaviorSubject=BehaviorSubject.create();
        behaviorSubject.onNext("A");
        behaviorSubject.onNext("B");

        behaviorSubject.subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.e(TAG,"asyncSubject accept= "+o);
            }
        });
        behaviorSubject.onNext("C");
        behaviorSubject.onNext("D");
      //  behaviorSubject.onComplete();
        Log.e(TAG,"============================");

        //ReplaySubject会接受到全部数据,不须调用onComplete()
        ReplaySubject<Object> replaySubject=ReplaySubject.create();
        replaySubject.onNext("A");
        replaySubject.onNext("B");
        replaySubject.subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.e(TAG,"replaySubject accept= "+o);
            }
        });
        replaySubject.onNext("C");
        replaySubject.onNext("D");
     //   replaySubject.onComplete();
        Log.e(TAG,"============================");

        //ReplaySubject只会接受订阅后的全部数据,不须调用onComplete()
        PublishSubject<Object> publishSubject=PublishSubject.create();
        publishSubject.onNext("A");
        publishSubject.onNext("B");

        publishSubject.subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.e(TAG,"publishSubject accept= "+o);
            }
        });
        publishSubject.onNext("C");
        publishSubject.onNext("D");
      //  publishSubject.onComplete();
    }
}
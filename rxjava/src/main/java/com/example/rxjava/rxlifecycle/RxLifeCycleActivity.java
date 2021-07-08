package com.example.rxjava.rxlifecycle;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.rxjava.R;
import com.example.rxjava.compose.SchedulerTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by cbd on 2021/7/8
 * RxJava内存泄露
 * 我们退出RxLifeCycleActivity后我们的请求还在后台执行（在线程中执行），并返回结果，但是我们去渲染界面(textView.setText("accept= "+o);)的时候
 * 1、如果这时控件为null（textView）、导致报空指针异常
 * 2、如果textView不为null说明我们的RxLifeCycleActivity泄露了，没有被gc回收
 */
public class RxLifeCycleActivity extends AppCompatActivity {
    public   final   String TAG=this.getClass().getSimpleName();
    TextView textView;
    //处理单个观察者的disposable
    Disposable disposable;
    //一般使用CompositeDisposable处理添加多个disposable一起处理
    CompositeDisposable compositeDisposable;
    //但是我们可能很容易忘掉 所以我们使用RxLifeCycle框架自动处理需要结合compose操作符

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         textView=findViewById(R.id.textView);
        // RxLifeCycle rxLifeCycle=new RxLifeCycle();
        // getLifecycle().addObserver(rxLifeCycle);
         compositeDisposable=new CompositeDisposable();
         disposable= Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                Log.e(TAG,"开始请求数据");
                Thread.sleep(6000);
                Log.e(TAG,"请求数据结束");
                emitter.onNext("request success");
                emitter.onComplete();

            }

        })
                 //使用compose实现代码的复用(替代subscribeOn和observeOn方法和我们的jetpack的LifeCycle解决内存泄露，不要每次都写这两个方法)，其apply方法传入一个上游的被观察者，然后返回的是一个新的被观察者
                 .compose(new SchedulerTransformer<>())
                 .compose(RxLifeCycle.bindRxLifeCycle(this))
                 //.subscribeOn(Schedulers.io())
                // .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Consumer<Object>() {
              @Override
              public void accept(Object o) {
                  Log.e(TAG,"accept= "+o);
                  textView.setText("accept= "+o);
              }
          });
        compositeDisposable.add(disposable);
    }


    //使用RxLifeCycle取消订阅（把CompositeDisposable放在RxLifeCycle进行管理，即add()和dispose()）
  /*  @Override
    protected void onDestroy() {
        super.onDestroy();
        //处理内存泄露
     //  disposable.dispose();
        compositeDisposable.dispose();
    }*/
}

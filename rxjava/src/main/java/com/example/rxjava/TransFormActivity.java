package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * 转换操作符
 */
public class TransFormActivity extends AppCompatActivity {
    public   final   String TAG=this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_form);
        testmap();
    }


    @SuppressLint("CheckResult")
    private  void testmap() {
        //我们的just等创建型操作符是Observable类的static方法，可以直接调用
        //而map，flatmap等转换操作符是Observable对象的实例 所以我们使用时先用创建操作符如just创建出Observable的实例,在使用转换操作符
        //map直接对被观察者进行处理，把原来发送出来的事件进行处理并且产生新的事件，再次创建新的被观察者，发射事件。它可以对数据类型进行转换。
       /* Observable.just("111")
                .map(new Function<String, Object>() {
                    @Override
                    public Object apply(@NotNull String s) throws Exception {
                        //这里把String转换成Object
                        return 111;
                    }
                }).subscribe(observer);*/
        //网络嵌套处理
        //flatmap Observable.just("去登录")产生的被观察者交给flatmap产生的被观察者
        //concatMap和flatMap基本一样，concatMap转发出来的事件是有序的，而flatMap是无序的
      /*  Observable.just("register")
                .flatMap(new Function<String, ObservableSource<?>>() {

                    @Override
                    public ObservableSource<?> apply(@NotNull String s) throws Exception {
                        Log.e(TAG, s + "成功");
                        return Observable.just("去登录");
                    }
        }).subscribe(observer);*/

    /*    Observable.just("1","3","4","2")
                .concatMap(new Function<String, ObservableSource<?>>() {

                    @Override
                    public ObservableSource<?> apply(@NotNull String s) throws Exception {
                        return Observable.just(s);
                    }
                }).subscribe(observer);*/

        //buffer缓存区域 一次发送三个
        Observable.just("1","3","4","2","22","222","22222")
                .buffer(3)
              .subscribe(observer);
    }

     Observer observer = new Observer<Object>() {
        @Override
        public void onSubscribe(@NotNull Disposable d) {
            Log.e(TAG, "onSubscribe " + d.isDisposed());
        }

        @Override
        public void onNext(@NotNull Object o) {
            Log.e(TAG, "onNext " + o);
        }

        @Override
        public void onError(@NotNull Throwable e) {
            Log.e(TAG, "onError " + e.toString());

        }

        @Override
        public void onComplete() {
            Log.e(TAG, "onComplete ");
        }
    };
}
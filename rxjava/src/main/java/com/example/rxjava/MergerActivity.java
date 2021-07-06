package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 组合操作符
 */
public class MergerActivity extends AppCompatActivity {
    public   final   String TAG=this.getClass().getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merger);
        testContact();
    }

    private void testContact(){
        Observable observable1=Observable.just("666");
        Observable observable2=Observable.just("777","888");
        Observable observable3=Observable.just("333","444","555");
        //组合操作符 contact将多个被观察者组合在一起，然后按照之前的发送顺序发送事件，contact只有4个构造函数最多只可以发送4个事件
        //concatArray 和contat一样，可以发送超过4个事件
        //merge和contact一样，只是contat是串行发送事件(一个发完另一个才能发)，merge是并行发送事件(哪个先处理完就先发)
        //zip 会将多个观察者合并，根据各个被观察者发送的顺序一个个结合起来，最终发送的事件数量会与源Observable中最少事件的数量一样，相当于压缩事件
        Observable.concatArray(observable1,observable2)
                //doOnNext 在observer onNext之前调用
                .doOnNext(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Log.e(TAG, "doOnNext accept" +o);
                    }
                })
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
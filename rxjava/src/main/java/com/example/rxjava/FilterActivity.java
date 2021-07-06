package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * 过滤操作符
 *
 */
public class FilterActivity extends AppCompatActivity {
    public   final   String TAG=this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        testfilter();
    }


    private void testfilter(){

        Observable.range(1,10)
                 .filter(new Predicate<Integer>() {
                     @Override
                     public boolean test(@NotNull Integer integer) throws Exception {
                         return integer<6;
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
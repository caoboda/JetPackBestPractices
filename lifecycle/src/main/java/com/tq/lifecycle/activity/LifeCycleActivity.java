package com.tq.lifecycle.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.tq.lifecycle.R;

/**
 * Created by cbd on 2021/6/17 17:28
 * 使用LifeCycle解耦系统组件（MainActivity）和普通的组件（MyChronometer）
 * 这样我们就不需要在Activity生命周期方法中处理相关业务逻辑从而达到解耦的目的
 */
public class LifeCycleActivity extends AppCompatActivity {

    private MyChronometer mychronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        mychronometer=findViewById(R.id.mychronometer);
        //被观察者添加观察者 如果我们的被观察者相关生命周期变化了 通知观察者mychronometer被注解的相关方法调用
        getLifecycle().addObserver(mychronometer);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("LifeCycleActivity","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("LifeCycleActivity","onPause");
    }
}

package com.tq.lifecycle.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;

import com.tq.lifecycle.R;
import com.tq.lifecycle.service.GpsActivity;

/**
 *  普通方式使用Chronometer计时 退出到后台时停止计时回到前台恢复计时
 * 问题：chronometer和我們的Activity組件的生命周期綁定了 耦合高
 * 解决：使用LifeCycle解耦系统组件（MainActivity）和普通的组件（Chronometer）
 */
public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private long elapsedRealtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer=findViewById(R.id.chronometer);
    }

    @Override
    protected void onResume() {
        super.onResume();
        chronometer.setBase(SystemClock.elapsedRealtime()-elapsedRealtime);
        chronometer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        elapsedRealtime=SystemClock.elapsedRealtime()-chronometer.getBase();
        chronometer.stop();
    }

    public void click(View view) {
        startActivity(new Intent(this,LifeCycleActivity.class));
    }
    public void click1(View view) {
        startActivity(new Intent(this, GpsActivity.class));
    }
}

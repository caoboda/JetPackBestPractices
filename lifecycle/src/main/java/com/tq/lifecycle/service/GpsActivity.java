package com.tq.lifecycle.service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tq.lifecycle.R;

/**
 * Created by cbd on 2021/6/17 18:09
 * Lifecycle解耦Service和组件
 * 通过LifeCycle获取后台Gps信息
 */
public class GpsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
    }

    public void startGps(View view){
     startService(new Intent(this,MyLocationService.class));
    }

    public void stopGps(View view){
        stopService(new Intent(this,MyLocationService.class));
    }
}

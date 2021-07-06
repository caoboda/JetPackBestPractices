package com.example.databinding.simple4;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.databinding.R;
import com.example.databinding.databinding.ActivityMain3Binding;
import com.example.databinding.databinding.ActivityMain4Binding;
import com.example.databinding.simple2.SuperStar1;

/**
 * 定义BindingAdapter加载网络图片
 * 加载本地图片及方法重载
 * 多参数重载
 *
 */
public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main4);
        ActivityMain4Binding activityMainBinding4= DataBindingUtil.setContentView(this,R.layout.activity_main4);
        activityMainBinding4.setNetWorkImage("https://p1.ssl.qhimg.com/t01f8dfa8b3955f24a2.jpg");
        activityMainBinding4.setLocalImage(R.mipmap.xxx);
    }
}
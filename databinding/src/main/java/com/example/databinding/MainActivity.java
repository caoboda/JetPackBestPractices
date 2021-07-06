package com.example.databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.databinding.databinding.ActivityMainBinding;

/**
 * databing基础应用显示
 * 明星信息展示
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
       ActivityMainBinding activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
       SuperStar superStar=new SuperStar("刘德华","五星");
       activityMainBinding.setSuperstar(superStar);
    }
}
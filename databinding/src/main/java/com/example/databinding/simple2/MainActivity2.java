package com.example.databinding.simple2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.databinding.R;
import com.example.databinding.databinding.ActivityMain2Binding;

/**
 * import标签导入StarUtils工具类
 * 和事件绑定EventHandeListener
 */
public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main2);
        ActivityMain2Binding activityMainBinding2= DataBindingUtil.setContentView(this,R.layout.activity_main2);
        SuperStar1 superStar=new SuperStar1("刘德华",4);
        activityMainBinding2.setSuperstar(superStar);
        //设置事件绑定EventHandeListener
        activityMainBinding2.setEventHandle(new EventHandeListener(this));
    }
}
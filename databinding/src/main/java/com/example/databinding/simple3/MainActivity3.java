package com.example.databinding.simple3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.example.databinding.R;
import com.example.databinding.databinding.ActivityMain3Binding;
import com.example.databinding.simple2.SuperStar1;

/**
 * 二级页面的绑定 使用include标签
 *
 */
public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main3);
        ActivityMain3Binding activityMainBinding3= DataBindingUtil.setContentView(this,R.layout.activity_main3);
        SuperStar1 superStar=new SuperStar1("刘德华",5);
        activityMainBinding3.setSuperstar(superStar);
    }
}
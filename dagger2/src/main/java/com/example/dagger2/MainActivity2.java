package com.example.dagger2;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;


import com.example.dagger2.di.ApiService;
import com.example.dagger2.di.binds.AInterface;
import com.example.dagger2.di.mutilcreate.Student;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * IOC 借助IOC容器（第三方）实现具有依赖关系之间的对象之间的解耦
 * 获得依赖对象掉的过程被反转了，控制被反转之后，获取依赖对象的过程由自身管理变成了由IOC容器（第三方）主动注入，这个控制反转过程被取名为依赖注入，依赖注入是实现IOC的一种方法
 *
 */
public class MainActivity2 extends AppCompatActivity {
    //apiService apiService2只是局部单例（在当前类），在其他Activity里面值不一样
    //因为apiSerVice（其默认作用域@Singleton）在另外一个Activity注入时， DaggerApplicationComponent.create()创建的是组另外一个Activity组件，它的生命周期而不是MainActivity的生命周期
    //解决方式：使用Application全局生命周期
    @Inject
    ApiService apiService;
    @Inject
    ApiService apiService2;

    @Inject
    AInterface aInterface;

    // @Named指定标识 不同的值不同的标识
    @Named("student1")
    @Inject
    Student student1;
    // @Named指定标识 不同的值不同的标识
    @Named("student2")
    @Inject
    Student student2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //第四步执行注入动作
       // DaggerApplicationComponent.create().inject(this);
        MyApplication.getApplicationComponent().inject(this);
        Log.e("apiService= ", apiService.toString());
        Log.e("apiService2= ", apiService2.toString());
        //接口实例
        Log.e("aInterface= ", aInterface.toString());

        //同一对象多种创建方式
        Log.e("student1= ", student1.toString());
        Log.e("student2= ", student2.toString());
    }
}
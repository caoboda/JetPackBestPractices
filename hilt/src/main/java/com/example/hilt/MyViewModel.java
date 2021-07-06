package com.example.hilt;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.ViewModel;

import dagger.hilt.android.qualifiers.ActivityContext;
import dagger.hilt.android.qualifiers.ApplicationContext;

/**
 * Created  by Administrator on 2021/6/30
 * Hilt多ViewModel的支持
 *
 * 前面我们对Model中的构造方法使用@Inject或者在ActivityModule使用@Provider注解来实现依赖注入
 * 但是jectpack里面的ViewModel不能使用上面所说的方式来实现依赖注入
 *
 *1、 要导入相关依赖：
 *     implementation 'androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha02'
 *     //java注解处理器
 *     annotationProcessor 'androidx.hilt:hilt-compiler:1.0.0-alpha02'
 *     //kotlin注解处理器
 *     kapt 'androidx.hilt:hilt-compiler:1.0.0-alpha02'
 *2、构造方法使用@ViewModelInject而不是之前的@Inject注解
 * 3、然后就可以把我们的参数传入构造方法中实现依赖注入
 */
public class MyViewModel extends ViewModel {
    User user;
    Application application;
    Activity activity;
    Context context;

    //这里的参数都需要建立绑定
    //User在AppModule中绑定 、Application application, Activity activity默认绑定、而Context需要加限定符(@ApplicationContextH或者@ActivityContext )绑定
    @ViewModelInject
    public MyViewModel(User user,Application application, Activity activity,@ApplicationContext Context context) {
        this.user=user;
        this.application=application;
        this.activity=activity;
        this.context=context;

    }


    @SuppressLint("LongLogTag")
    protected void test()  {
        Log.e("MyViewModel user====== ",user.toString());
        Log.e("MyViewModel application====== ",application.toString());
        Log.e("MyViewModel activity===== ",activity.toString());
        Log.e("MyViewModel context===== ",context.toString());

    }

}

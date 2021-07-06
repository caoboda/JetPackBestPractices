package com.example.hilt;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

/**
 * Created  by Administrator on 2021/6/30
 * 预定义绑定及限定符
 */
//已经绑定的的类
public class Model {
    User user;
    //Hilt提供几种预定义的绑定(提供对象实例的获取称为绑定)：如：Application实例或者Activity实例的获取
    //Application或者Activity由Hilt预定义绑定，不需要我们处理
    Application application;
    Activity activity;
   // Hilt提供集几种预定义的限定符（Qualifier）,在注入的地方使用@ApplicationContext和@ActivityContext获取ApplicationContext
    //否则报错
    Context context;

    //2、建立绑定 提供对象实例的获取称为绑定
    //一般方式：通过@Inject注解在构造方法上 告知Dagger可以通过构造方法来创建Model对象的实例
    //User user已经在AppModule中建立绑定了，所以可以通过构造方法直接传递，从而在Model里面获取user的实例
     //@Inject
    public Model(User user,Application application, Activity activity,Context context) {
       this.user=user;
       this.application=application;
       this.activity=activity;
        this.context=context;

    }



    protected void test()  {
        Log.e("user====== ",user.toString());
        Log.e("application====== ",application.toString());
        Log.e("activity===== ",activity.toString());
        Log.e("context===== ",context.toString());

    }
}

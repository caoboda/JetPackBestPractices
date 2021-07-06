package com.example.hilt;

import javax.inject.Inject;

/**
 * Created  by Administrator on 2021/6/30
 */
public class User {
    //2、建立绑定 提供对象实例的获取称为绑定
    //一般方式：通过@Inject注解在构造方法上 告知Dagger可以通过构造方法来创建User对象的实例
  /*  @Inject
    public User() {

    }*/

    //Hilt提供几种预定义的绑定(提供对象实例的获取称为绑定)使用Module注解在类上的方式见AppModule
   public User() {

    }
}

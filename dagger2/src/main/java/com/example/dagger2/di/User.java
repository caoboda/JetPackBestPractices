package com.example.dagger2.di;

import javax.inject.Inject;
/**
 * Created  by Administrator on 2021/6/28
 */
public class User {
    //第一种依赖注入方式：构造方法注入
    //使用Dagger快速注入实例对象user对象到Manactivity中
    //第一步
    //使用 @Inject注解在构造方法上，其实就是告知Dagger可以通过构造方法来创建并获取User类的实例
    @Inject
    public User(){

    }
}

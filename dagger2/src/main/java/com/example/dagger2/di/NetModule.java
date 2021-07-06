package com.example.dagger2.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
/**
 * //Dagger模块
 * Created  by Administrator on 2021/6/28
 */
//使用@Module注解的类来注入第三方框架，因为不能在第三方构造方法上添加@Inject注入第三方框架的对象如：Retrofit

@Module
public class NetModule {

    //也可以注入我们的User对象
    @Provides
    public User providrerUser(){
     return new User();
    }

    //第二种依赖注入方式：通过模块注入 然后把模块装载到我们的Component组件当中
    //告知Dagger，可以通过调用该方法来获取到要注入对象的实例

    //同理如果我们当前模块提供了获取OkHttpClient实例的方式，已经知道了怎么去获取okHttpClient的实例，我们可以通过参数（OkHttpClient okHttpClient）直接传递进去
    @Singleton//如果我们
    @Provides
    public Retrofit providerRetrofit(OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://www.baidu.com")
                .build();
    }

    //如果我们当前模块提供了获取Retrofit实例的方式，已经知道了怎么去获取retrofit的实例，我们可以通过参数（Retrofit retrofit）直接传递进去
    @Singleton//默认作用域 如下第1点解释(因为某个对象的生命周期限定为其组件的生命周期)所以组件（ApplicationComponent）也要加使用默认作用域@Singleton
    @Provides
    public ApiService providerApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }

    //  @Singleton是Dagger提供的一种作用域实现
    //作用域就是用于管理Component来获取对象实例的生命周期的
    @Singleton
    @Provides
    public OkHttpClient providerOkHttpClient(){
        return new OkHttpClient.Builder()
                .build();
    }

    //然后问题来了，我们的对象不能频繁的创建对象，需要使用作用域注解
    //1、它可以将某个对象的生命周期限定为其组件的生命周期，也就是，在作用域范围内，使用的是同一实例。
    //2、@Singleton是Dagger提供的一张默认的作用域注解，表示一个单例对象。也就是实例的生命周期和程序的生命周期保持一致
    //3、作用域注解使用在@Inject、@Provides、@Binds、@Module、@Component注解上，表示其产生作用的范围
}

package com.example.hilt;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ActivityContext;
import dagger.hilt.android.scopes.ActivityScoped;

/**
 * Created  by Administrator on 2021/6/30
 * 在Hilt中使用Module
 */
//3、@InstallIn()把我们的模块装载到那个组件上面
//@InstallIn(ActivityComponent.class)
@InstallIn(ApplicationComponent.class)
@Module
public class AppModule {

    //如果使用 @InstallIn(ApplicationComponent.class),由于ApplicationComponent上面使用了@Singleton作用域，所以如果这里要使用也只能使用@Singleton作用域
    //如果使用 @InstallIn(ActivityComponent.class) 则使用@ActivityScoped作用域
    @Singleton
   // @ActivityScoped
    @Provides
     User provideUser(){
        return new User();
    }
}

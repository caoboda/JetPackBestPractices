package com.example.hilt;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.android.scopes.ActivityScoped;

/**
 * Created  by Administrator on 2021/6/30
 */
@InstallIn(ActivityComponent.class)
@Module
public class ActivityModule {

    //这种绑定 Model上的构造@Inject去掉
    //这里不能使用ApplicationComponent.class 因为ActivityComponent默认绑定Application和Activity而ApplicationComponent默认绑定Application
    //下面构造函数的Activity不能在ApplicationComponent中使用(默认只绑定Application没有绑定Activity)
    @ActivityScoped
    @Provides
    Model provideModel(User user, Application application, Activity activity,@ApplicationContext Context context){
        return new Model(user,application,activity,context);
    }
}

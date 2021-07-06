package com.example.dagger2;

import android.app.Application;
import com.example.dagger2.di.ApplicationComponent;
import com.example.dagger2.di.DaggerApplicationComponent;

/**
 * Created  by Administrator on 2021/6/29
 */
public class MyApplication extends Application {
    static ApplicationComponent applicationComponent= DaggerApplicationComponent.create();

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}

package com.example.room;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;


/**
 * Created by cbd on 2021/6/10 16:47
 */
public class BaseAplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}

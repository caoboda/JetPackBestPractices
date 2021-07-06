package com.example.hilt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * 组件与作用域的关系：
 * 如果AppModule 使用@InstallIn(ActivityComponent.class) 表示在Activiy的作用域ActivityScoped MainActivity和MainActivity2的user对象不是同一个
 * 如果AppModule 使用@InstallIn(ApplicationComponent.class) 表示在Application的作用域@Singleton(全局单例)  MainActivity和MainActivity2的user对象是同一个
 */
@AndroidEntryPoint
public class MainActivity2 extends AppCompatActivity {
    @Inject
    User user1;

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.e("MainActivity2 super Class= ",this.getClass().getSuperclass().getSimpleName());
        Log.e("user1= ",user1.toString());
    }
}
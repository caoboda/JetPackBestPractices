package com.example.databinding.simple2;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * Created  by Administrator on 2021/6/19
 * 点击事件处理
 */
public class EventHandeListener {
    private Context context; 
    private boolean flag=false;

    public EventHandeListener(Context context) {
        this.context = context;
    }

    public void buttonClick(View view){
        if(flag){
            Toast.makeText(context,"取消喜欢",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"喜欢",Toast.LENGTH_SHORT).show();
        }
        flag=!flag;

    }
}

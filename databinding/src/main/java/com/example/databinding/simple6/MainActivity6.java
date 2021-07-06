package com.example.databinding.simple6;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.databinding.R;
import com.example.databinding.databinding.ActivityMain5Binding;
import com.example.databinding.databinding.ActivityMain6Binding;
import com.example.databinding.simple5.UserViewModel;
import com.example.databinding.simple5.UserViewModel2;

import java.util.ArrayList;
import java.util.List;

/**
 *RecycleView的绑定
 */
public class MainActivity6 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main6);
        ActivityMain6Binding activityMainBinding6= DataBindingUtil.setContentView(this,R.layout.activity_main6);
        List<User> userList=new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            User user=new User("刘德华"+i,"liudehua","https://p1.ssl.qhimg.com/t01f8dfa8b3955f24a2.jpg");
            userList.add(user);
        }
        MyRecycleViewAdapter myRecycleViewAdapter=new MyRecycleViewAdapter(userList);
        activityMainBinding6.recycleview.setLayoutManager(new LinearLayoutManager(this));
        activityMainBinding6.recycleview.setAdapter(myRecycleViewAdapter);
    }
}
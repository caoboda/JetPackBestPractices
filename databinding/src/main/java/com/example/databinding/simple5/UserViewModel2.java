package com.example.databinding.simple5;

import android.text.TextUtils;
import android.util.Log;

import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

import com.example.databinding.BR;

/**
 * Created  by Administrator on 2021/6/19 16:41
 * 双向绑定ObservableField
 */
public class UserViewModel2{

    private ObservableField<User> userObservableField;


    public UserViewModel2(){
        userObservableField=new ObservableField<>();
        User user=new User("cbd");
        userObservableField.set(user);
    }

    public String getUserName(){
        return userObservableField.get().userName;
    }


    public void setUserName(String userName){
        Log.e("UserViewModel2","set userObservableField userName: "+userName);
        userObservableField.get().userName=userName;
    }


}

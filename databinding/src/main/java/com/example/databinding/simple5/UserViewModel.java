package com.example.databinding.simple5;

import android.text.TextUtils;
import android.util.Log;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.example.databinding.BR;

/**
 * Created  by Administrator on 2021/6/19 16:41
 * 双向绑定BaseObservable
 */
public class UserViewModel extends BaseObservable {

    private User user;


    public UserViewModel(){
        this.user=new User("cbd");
    }

    // @Bindable注解的属性  只要user.userName发生变化，就会让EditText控件的内容发生变化
    @Bindable
    public String getUserName(){
       return  user.userName;
    }

    //只要EditText内容发生变化 自动调用该方法 同步数据到user.userName属性
    public void setUserName(String userName){
        //EditText内容不为空 且发生变化
        if(!TextUtils.isEmpty(userName) && !userName.equals(user.userName)){
            user.userName=userName;
            Log.e("UserViewModel","set userName: "+userName);
            notifyPropertyChanged(BR.userName);
        }
    }

}

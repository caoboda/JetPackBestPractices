package com.example.databinding.simple5;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.databinding.R;
import com.example.databinding.databinding.ActivityMain4Binding;
import com.example.databinding.databinding.ActivityMain5Binding;

/**
 *双向绑定BaseObservable与ObservableField
 */
public class MainActivity5 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main5);
        ActivityMain5Binding activityMainBinding5= DataBindingUtil.setContentView(this,R.layout.activity_main5);
        activityMainBinding5.setUserViewModelBaseObservable(new UserViewModel());
        activityMainBinding5.setUserViewModelObservableField(new UserViewModel2());
    }
}
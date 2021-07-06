package com.example.databinding.simple7;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.databinding.R;
import com.example.databinding.databinding.ActivityMain6Binding;
import com.example.databinding.databinding.ActivityMain7Binding;
import com.example.databinding.simple6.MyRecycleViewAdapter;
import com.example.databinding.simple6.User;

import java.util.ArrayList;
import java.util.List;

/**
 *DataBinding+ViewModel+LiveData
 * 实现篮球计分
 */
public class MainActivity7 extends AppCompatActivity {

    private ScoreViewModel scoreViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main7);
        ActivityMain7Binding activityMainBinding7= DataBindingUtil.setContentView(this,R.layout.activity_main7);
         scoreViewModel= new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(ScoreViewModel.class);
        activityMainBinding7.setViewModel(scoreViewModel);
        activityMainBinding7.setLifecycleOwner(this);
    }
}
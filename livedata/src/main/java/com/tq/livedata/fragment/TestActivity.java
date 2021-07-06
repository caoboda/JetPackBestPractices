package com.tq.livedata.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tq.livedata.R;

/**
 * ViewModel+LiveData实现Fragment之间通信
 */
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
}
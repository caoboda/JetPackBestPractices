package com.example.rxjava.permission;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.rxjava.R;
import com.example.rxjava.rxbus.RxBus;
import com.example.rxjava.rxlifecycle.RxLifeCycle;
import com.jakewharton.rxbinding4.view.RxView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;
import kotlin.Unit;

/**
 * permission、rxbinding
 */
public class PerMissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_mission);
        TextView textView2=findViewById(R.id.textView2);
        RxView.clicks(textView2)
                //防止快速点击 1s内只能点击一次
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new io.reactivex.rxjava3.functions.Consumer<Unit>() {
                    @Override
                    public void accept(Unit unit) throws Throwable {
                        Log.e("RxView clicks = ","click");
                    }
                });
        cameraPersion();
    }

    @SuppressLint("CheckResult")
    private void cameraPersion() {
        new RxPermissions(this).request("android.permission.CAMERA")
                .compose(RxLifeCycle.bindRxLifeCycle(this))
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if(aBoolean){
                            Log.e("camera persion accept= ",""+ aBoolean);
                        }
                    }
                });
    }
}
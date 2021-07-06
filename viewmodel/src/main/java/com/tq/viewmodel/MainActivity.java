package com.tq.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
/**
 * ViewModel处理屏幕旋转数据的丢失
 */
public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView);
        myViewModel= new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MyViewModel.class);
        textView.setText(String.valueOf(myViewModel.numCount));
    }

    public void plusNum(View view) {
        textView.setText(String.valueOf(++myViewModel.numCount));
    }
}

package com.tq.livedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.tq.livedata.fragment.TestActivity;
import java.util.Timer;
import java.util.TimerTask;

/**ViewModel一般结合LiveData一起使用 可以避免横竖屏数据丢失及内存泄露问题和减少Activity代码膨胀数据一般放在ViewModel中处理
 * LiveData小应用 改变时间
 * 一般写法是开个线程去增加时间 然后通过handler去更新ui 而通过handler会简单很多
 *
 * LiveData优点
 * 1、确保界面是最新的数据状态。
 * 2、不会发生内存泄漏。
 * 3、不会因Activity停止而发生崩溃。
 * 4、不需要手动处理生命周期。
 * 5、数据始终保持最新状态。
 * 6、适当的配置更改(屏幕旋转)不会导致数据的变化
 * 7、共享资源
 */
public class MainActivity extends AppCompatActivity {
    private String TAG = this.getClass().getName();

    private TextView time_tv;
    private TimeViewModel timeViewModel;
    private Timer timer;
    private MyTimerTask myTimerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time_tv=findViewById(R.id.time_tv);
        timeViewModel= new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(TimeViewModel.class);
        time_tv.setText(String.valueOf(timeViewModel.getCurrentTime().getValue()));
        timeViewModel.startTimer();
        //监听LiveData数据改变
        timeViewModel.getCurrentTime().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                time_tv.setText(String.valueOf(integer));
            }
        });

        //startTimer();
    }

    //于屏幕旋转导致的Activity重建，导致每次旋转timer和myTimerTask都会置为空 然后每次创建timer和myTimerTask并调用timer的schedule方法
    //每次旋转会新开一个线程增加timeViewModel.getCurrentTime().getValue()的值的大小，从而开导致timeViewModel.getCurrentTime().getValue()的值变化很快而不是每次加1
    //所以我们把创建timer和myTimerTask的工作放在ViewModel中去处理 只有ViewModel已经没有任何Activity与之有关联，系统则会调用onCleared（）方法，你可以在此清理资源（currentTime、timer和myTimerTask等）
    private void startTimer() {
      /*  if(timer!=null){
            timer.cancel();
            timer=null;
        }
        if(myTimerTask!=null){
            myTimerTask.cancel();
            myTimerTask=null;
        }*/
        Log.e(TAG,"roate");
        timer= new Timer();
        myTimerTask=new MyTimerTask();
        //1s后每隔1s更新一次livedata的数据
        timer.schedule(myTimerTask,1000,1000);
    }



    class MyTimerTask extends TimerTask{

        @Override
        public void run() {
            //非UI线程 postValue()
            //UI线程 setValue()
            timeViewModel.getCurrentTime().postValue(timeViewModel.getCurrentTime().getValue()+1);
        }
    }


    public void click(View view) {
        startActivity(new Intent(this, TestActivity.class));
    }
}

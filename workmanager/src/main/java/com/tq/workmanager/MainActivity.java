package com.tq.workmanager;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Operation;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
/**
 * WorkManager
 * 诞生背景：
 * 在后台执行任务的需要是很常见的，Android提供了多种方式，如JobScheduler、Loader、Service，若这些API没有正确的使用，则可能消耗大量的电量。
 * 作用：
 * WorkManager为应用程序中那些不需要及时完成的任务提供了统一的解决方案，以便在用户体验和电量之间达到一个平衡的效果。
 * 特点：
 * 1、针对的是不需要及时完成的任务
 * 如发送日志、同步应用程序数据、备份数据，这些任务一般都不需要立即完成，如果我们自己来完成这些任务，
 * 逻辑可能会很复杂，若API使用不当，可能导致消耗大量电量。它不能保证任务立刻执行。
 * 2、任务一定会被执行
 * WorkManager能保证任务一定会被执行，即使应用程序不在执行，甚至在设备重启后，任务仍然会在合适的时刻执行，因为WorkManager有自己的数据库，任务的所有信息和数据都保存在数据库中，
 * 因此只要任务交给了WorkManager，哪怕是应用程序彻底退出，或者设备重启，WorkManager仍然能够保证任务的执行。
 * 3、兼容范围广
 * 1、支持API Level 14(Android 4.0版本),不需要安装Google Play Services,而JobScheduler是Android从5.0增加支持一种特殊的机制,即任务调度，因此兼容性更好。
 * WorkManager的兼容方案
 * WorkManager能根据设备的情况，选择不同的执行方案API Level 21(Android 5.0)以上的设备通过JobScheduler完成任务，API Level 21(Android 5.0)以下的设备通过AlarmManager和Broadcast Receives组合来完成任务，
 * 但无论采用哪种方式，都是通过Executor来执行的。
 */

public class MainActivity extends AppCompatActivity {
    public final  static  String TAG=MainActivity.class.getSimpleName();
    private WorkManager workManager;
    private OneTimeWorkRequest oneTimeWorkRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addWorker(View view) {
        Constraints constraints=new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        Data inputData=new Data.Builder()
                .putString("input_data","666")
                .build();
        //创建任务
        //一次性执行任务
         oneTimeWorkRequest= new  OneTimeWorkRequest.Builder(MyWork.class)
                //设置约束条件
                 .setConstraints(constraints)
                //设置延时执行 3s后执行
                 .setInitialDelay(3, TimeUnit.SECONDS)
                //设置指数退避策略(相当于失败后定时重连) doWork()需返回return Result.retry()
                .setBackoffCriteria(BackoffPolicy.LINEAR,3, TimeUnit.SECONDS)
                //设置TAG标签
                .addTag("oneTimeWorkRequest")
                 //传递参数
                 .setInputData(inputData)
                .build();
         //周期性任务
        //不能少于15分钟 如果少于15分钟则按15分钟处理
        PeriodicWorkRequest periodicWorkRequest=new PeriodicWorkRequest.Builder(MyWork.class, Duration.ofMinutes(15))
                .build();
        //任务提交给addWorkerWorkManager
        workManager= WorkManager.getInstance(this);
        //点击按钮不会立马执行 等几秒后执行
        workManager.enqueue(oneTimeWorkRequest);
        //监听任务状态
        workManager.getWorkInfoByIdLiveData(oneTimeWorkRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
               // Log.e(TAG,"workInfo= "+workInfo.toString());
                if(workInfo!=null && workInfo.getState()==WorkInfo.State.SUCCEEDED){
                   String output_data=workInfo.getOutputData().getString("output_data");
                    Log.e(TAG,"output_data= "+output_data);
                }
            }
        });
        //2s后取消任务
       /* new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Operation operation= workManager.cancelWorkById(oneTimeWorkRequest.getId());
                try {
                    //获取取消任务的状态
                    Log.e(TAG,"operation= "+operation.getResult().get());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 10000);*/
    }
}

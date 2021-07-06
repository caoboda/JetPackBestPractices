package com.tq.workmanager;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
/**
 * Created by cbd on 2021/6/25
 * 定义任务和任务执行结果
 */
public class MyWork extends Worker {
    public final  static  String TAG=MyWork.class.getSimpleName();

    public MyWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        String input_data= getInputData().getString("input_data");
        Log.e(TAG,"MyWork doWork..."+" input_data="+input_data);
        SystemClock.sleep(3000);
        //不成功就重试
        Data output_data=new Data.Builder()
                .putString("output_data","doWork Success")
                .build();
        return Result.success(output_data);
       // return Result.retry();
    }
}

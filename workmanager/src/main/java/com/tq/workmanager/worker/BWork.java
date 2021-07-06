package com.tq.workmanager.worker;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * Created by cbd on 2021/6/25
 * 定义任务和任务执行结果
 */
public class BWork extends Worker {
    public final  static  String TAG= BWork.class.getSimpleName();

    public BWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.e(TAG,this.getClass().getSimpleName()+" doWork...");
        SystemClock.sleep(3000);
        return Result.success();
       // return Result.retry();
    }
}

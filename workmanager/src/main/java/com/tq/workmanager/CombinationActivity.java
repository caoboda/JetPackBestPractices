package com.tq.workmanager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkContinuation;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.tq.workmanager.worker.AWork;
import com.tq.workmanager.worker.BWork;
import com.tq.workmanager.worker.CWork;
import com.tq.workmanager.worker.DWork;
import com.tq.workmanager.worker.EWork;

import java.util.Arrays;

/**
 * Created by cbd on 2021/6/25
 */
public class CombinationActivity extends AppCompatActivity {

    private static final String TAG =CombinationActivity.class.getSimpleName() ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void addWorker(View view) {
        Data inputData=new Data.Builder()
                .putString("input_data","666")
                .build();

        //一次性执行任务
        OneTimeWorkRequest awork = new OneTimeWorkRequest.Builder(AWork.class)
                .setInputData(inputData)
                .build();

        OneTimeWorkRequest bwork = new OneTimeWorkRequest.Builder(BWork.class)
                .build();

        OneTimeWorkRequest cwork = new OneTimeWorkRequest.Builder(CWork.class)
                .setInputData(inputData)
                .build();

        OneTimeWorkRequest dwork = new OneTimeWorkRequest.Builder(DWork.class)
                .build();

        OneTimeWorkRequest ework = new OneTimeWorkRequest.Builder(EWork.class)
                .build();
        //任务提交给addWorkerWorkManager
        WorkManager workManager = WorkManager.getInstance(this);
        //任务链 先执行awork再执行bwork
     /*   workManager
                .beginWith(awork)
                .then(bwork)
                .enqueue();*/

        //任务链组合 先执行A、B和C、D，在执行E
        //A、B、C、D沒有先后顺序，但在E之前
        //任务组合
        WorkContinuation workContinuation1=WorkManager.getInstance(this)
                .beginWith(awork)
                .then(bwork);

        WorkContinuation workContinuation2=WorkManager.getInstance(this)
                .beginWith(cwork)
                .then(dwork);

        //执行任务链组合
        WorkContinuation.combine(Arrays.asList(workContinuation1,workContinuation2)).then(ework).enqueue();




        //监听任务状态
        workManager.getWorkInfoByIdLiveData(awork.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                // Log.e(TAG,"workInfo= "+workInfo.toString());
                /*if(workInfo!=null && workInfo.getState()==WorkInfo.State.SUCCEEDED){
                    String output_data=workInfo.getOutputData().getString("output_data");
                    Log.e(TAG,"output_data= "+output_data);
                }*/
            }
        });

    }
}

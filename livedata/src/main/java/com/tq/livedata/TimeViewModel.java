package com.tq.livedata;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by cbd on 2021/6/18 15:57
 */
public class TimeViewModel extends ViewModel {
    private String TAG = this.getClass().getName();
    private MyTimerTask myTimerTask;
    private Timer timer;
    public MutableLiveData<Integer> currentTime;



    public MutableLiveData<Integer> getCurrentTime() {
        if(currentTime==null){
            currentTime=new MutableLiveData<>();
            //初始化当前时间
            currentTime.setValue(0);
        }
        return currentTime;
    }

    public void startTimer() {
        //于屏幕旋转导致的Activity重建，导致每次旋转timer和myTimerTask都会置为空 然后每次创建timer和myTimerTask并调用timer的schedule方法
        //每次旋转会新开一个线程增加timeViewModel.getCurrentTime().getValue()的值的大小，从而开导致timeViewModel.getCurrentTime().getValue()的值变化很快而不是每次加1
        //所以我们把创建timer和myTimerTask的工作放在ViewModel中去处理(Activity重建后Viewmodel不会重置，timer和myTimerTask不会重置 我们判断不为null后手动取消任务执行，然后再重新创建就能解决2的问题) 只有ViewModel已经没有任何Activity与之有关联，系统则会调用onCleared（）方法，你可以在此清理资源（currentTime、timer和myTimerTask等）
        if(timer!=null){
            timer.cancel();
            timer=null;
        }
        if(myTimerTask!=null){
            myTimerTask.cancel();
            myTimerTask=null;
        }
        Log.e(TAG,"roate");
        timer= new Timer();
        myTimerTask=new MyTimerTask();
        //1s后每隔1s更新一次livedata的数据
        timer.schedule(myTimerTask,1000,1000);
    }


    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            //非UI线程 postValue()
            //UI线程 setValue()
            currentTime.postValue(currentTime.getValue()+1);
        }
    }

    /**
     * 由于屏幕旋转导致的Activity重建，该方法不会被调用
     *
     * 只有ViewModel已经没有任何Activity与之有关联，系统则会调用该方法，你可以在此清理资源 以免内存泄漏
     * */
    @Override
    protected void onCleared()
    {
        super.onCleared();
        Log.e(TAG, "onCleared()");
        if(timer!=null){
            timer.cancel();
            timer=null;
        }
        if(myTimerTask!=null){
            myTimerTask.cancel();
            myTimerTask=null;
        }
    }

}

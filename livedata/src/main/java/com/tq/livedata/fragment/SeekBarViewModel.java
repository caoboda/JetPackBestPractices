package com.tq.livedata.fragment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
/**
 * Created by cbd on 2021/6/18
 * 实现2个Fragment之间SeekBar之间的联动(共享数据)
 */
public class SeekBarViewModel extends ViewModel {

    private MutableLiveData<Integer> mProgress;

    public MutableLiveData<Integer> getmProgress() {
        if(mProgress==null){
            mProgress=new MutableLiveData<>();
            mProgress.setValue(0);
        }
        return mProgress;
    }
}

package com.example.databinding.simple7;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created  by Administrator on 2021/6/19 18:38
 */
public class ScoreViewModel  extends ViewModel {
    private MutableLiveData<Integer> aTeamScore;
    private MutableLiveData<Integer> bTeamScore;
    private Integer mLasta;
    private Integer mLastb;


    public MutableLiveData<Integer> getaTeamScore() {
        if(aTeamScore==null){
            aTeamScore=new MutableLiveData<>();
            aTeamScore.setValue(0);
        }
        return aTeamScore;
    }

    public MutableLiveData<Integer> getbTeamScore() {
        if(bTeamScore==null){
            bTeamScore=new MutableLiveData<>();
            bTeamScore.setValue(0);
        }
        return bTeamScore;
    }

    //add ateam score
    public void aTeamScoreAdd(int i){
        saveLastScore();
        aTeamScore.setValue(aTeamScore.getValue()+i);
    }
    //add bteam score
    public void bTeamScoreAdd(int i){
        saveLastScore();
        bTeamScore.setValue(bTeamScore.getValue()+i);
    }

    //撤销
    public void undo(){
        aTeamScore.setValue(mLasta);
        bTeamScore.setValue(mLastb);
    }

    //撤销
    public void reset(){
        aTeamScore.setValue(0);
        bTeamScore.setValue(0);
    }

    //记录上一次的分数
    public void saveLastScore(){
        mLasta=aTeamScore.getValue();
        mLastb=bTeamScore.getValue();
    }
}

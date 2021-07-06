/*
package com.tq.paging;

import android.app.Application;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.paging.PagedList;


*/
/**
 * Created  by Administrator on 2021/6/23
 *//*

public class AdvisoryBoundaryCallBack extends PagedList.D<AdvisoryInfo.DataBean.RecordsBean>{
    public static  int PAGENUM=1;//页数
    public static  int PAGESIZE=10;//每页条数
    private Application application;
    private AdvisoryRepository advisoryRepository;
    private LifecycleOwner lifecycleOwner;
    private AdvisoryViewmodel viewmodel;

    public AdvisoryBoundaryCallBack(AdvisoryViewmodel viewmodel, Application application, AdvisoryRepository advisoryRepository, LifecycleOwner lifecycleOwner) {
        this.viewmodel=viewmodel;
        this.application = application;
        this.advisoryRepository = advisoryRepository;
        this.lifecycleOwner = lifecycleOwner;
    }

    @Override
    public void onZeroItemsLoaded() {
        super.onZeroItemsLoaded();
        //第一页数据
        loadFirstPage();
    }

    private void loadFirstPage() {
        flag=false;
        PAGENUM=1;
        advisoryRepository.getAdvisoryList(PAGENUM, PAGESIZE, 0, new int[]{}, lifecycleOwner, new ICallback<AdvisoryInfo.DataBean>() {


            @Override
            public void onSuccess(AdvisoryInfo.DataBean data) {
               //保存到数据库
                insertToAdvisory(data.getRecords());
            }

            @Override
            public void onFailure(ApiException e) {
                Logger.e("e= "+e.getErrorMessage());
                viewmodel.statusLiveData.setValue(e);
            }
        });
    }

    //把网路数据保存到数据库
    private void insertToAdvisory(List<AdvisoryInfo.DataBean.RecordsBean> list) {
        AsyncTask.execute(() -> MyDatabase.getmInstance(application).getAdvisoryDao().insertToRecord(list));
    }

    @Override
    public void onItemAtEndLoaded(@NonNull @NotNull AdvisoryInfo.DataBean.RecordsBean itemAtEnd) {
        super.onItemAtEndLoaded(itemAtEnd);
        //下一页数据
        loadNextPage();
    }

    boolean flag=false;
    private void loadNextPage() {
        if(flag){

        }else{
            PAGENUM= 2;
        }
        advisoryRepository.getAdvisoryList(PAGENUM, PAGESIZE, 0, new int[]{}, lifecycleOwner, new ICallback<AdvisoryInfo.DataBean>() {

            @Override
            public void onSuccess(AdvisoryInfo.DataBean data) {
                flag=true;
                //如果有下一页
                if(data!=null){
                    if(data.getCurrent()*data.getSize()<data.getTotal()){
                        PAGENUM=PAGENUM+1;
                        //保存到数据库
                        insertToAdvisory(data.getRecords());
                    }else{

                    }
                }
            }

            @Override
            public void onFailure(ApiException e) {
                Logger.e("e= "+e.getErrorMessage());
                viewmodel.statusLiveData.setValue(e);
            }
        });
    }
}
*/

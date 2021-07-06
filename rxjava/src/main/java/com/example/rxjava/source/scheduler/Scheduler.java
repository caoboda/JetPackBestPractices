package com.example.rxjava.source.scheduler;

/**
 * Created  by Administrator on 2021/7/5
 */
public abstract class Scheduler {

    public abstract Worker createWorke();

    public  interface Worker{
        void scheduler(Runnable runnable);
    }
}

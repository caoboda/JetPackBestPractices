package com.example.rxjava.source.scheduler;

import android.os.Handler;

/**
 * Created  by Administrator on 2021/7/5
 */
public class HandlerSheduler  extends  Scheduler{
    Handler handler;

    public HandlerSheduler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public Worker createWorke() {
        return new HandlerWorker(handler);
    }

    static final  class HandlerWorker implements Worker{
        final Handler handler;

        public HandlerWorker(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void scheduler(Runnable runnable) {
            //切换到主线程执行
            handler.post(runnable);
        }
    }
}

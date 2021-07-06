package com.example.rxjava.source.scheduler;

import java.util.concurrent.ExecutorService;

/**
 * Created  by Administrator on 2021/7/5
 */
public class NewThreadScheduler extends Scheduler{
    final ExecutorService executorService;

    public NewThreadScheduler(ExecutorService executorService) {
        this.executorService = executorService;
    }


    @Override
    public Worker createWorke() {
        return new NewThreadWorker(executorService);
    }

    static  final  class  NewThreadWorker implements Worker{

        final ExecutorService executorService;

        NewThreadWorker(ExecutorService executorService) {
            this.executorService = executorService;
        }

        @Override
        public void scheduler(Runnable runnable) {
            executorService.execute(runnable);
        }
    }
}

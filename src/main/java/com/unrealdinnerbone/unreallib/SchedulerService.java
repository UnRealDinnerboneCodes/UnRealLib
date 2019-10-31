package com.unrealdinnerbone.unreallib;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SchedulerService {

    public final static SchedulerService SCHEDULER_SERVICE = new SchedulerService();


    private final ExecutorService executorService;

    public SchedulerService() {
        this.executorService = Executors.newScheduledThreadPool(Math.max(1, Runtime.getRuntime().availableProcessors() - 1), new ThreadFactoryBuilder().setNameFormat("scheduler-%d").setUncaughtExceptionHandler(new SchedulerExceptionHandler()).build());
        ShutdownUtils.addShutdownHook(this::shutdown);
    }

    public void execute(Runnable task) {
        executorService.execute(task);
    }

    public void shutdown() {
        executorService.shutdown();
    }

    public void forceShutdown() {
        executorService.shutdownNow();
    }

    static class SchedulerExceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            e.printStackTrace();
        }
    }

}

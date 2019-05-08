package com.unrealdinnerbone.unreallib;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskScheduler {

    private static final ScheduledExecutorService executor  = Executors.newScheduledThreadPool(Math.max(1, Runtime.getRuntime().availableProcessors() - 1), new ThreadFactoryBuilder().setNameFormat("scheduler-%d").setUncaughtExceptionHandler(new TaskExceptionHandler()).build());

    public static void scheduleAtFixedRate(int time, TimeUnit timeUnit, Runnable runnable) {
        executor.scheduleAtFixedRate(runnable, 0, time, timeUnit);
    }
    public static void handleTaskOnThread(int time, TimeUnit timeUnit, Runnable runnable) {
        executor.schedule(runnable, time, timeUnit);
    }

    @Slf4j
    public static class TaskExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            log.error("Error while handling task on thread " + t.getName(), e);
        }
    }
}

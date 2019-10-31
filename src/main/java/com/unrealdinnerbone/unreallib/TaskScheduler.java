package com.unrealdinnerbone.unreallib;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;

import java.util.concurrent.*;

public class TaskScheduler {

//    private static final ScheduledExecutorService executor  = Executors.newScheduledThreadPool(Math.max(1, Runtime.getRuntime().availableProcessors() - 1), new ThreadFactoryBuilder().setNameFormat("scheduler-%d").setUncaughtExceptionHandler(new TaskExceptionHandler()).build());
//
//    public static void scheduleAtFixedRate(int time, TimeUnit timeUnit, Runnable runnable) {
//        executor.scheduleAtFixedRate(runnable, 0, time, timeUnit);
//    }
//    public static void handleTaskOnThread(int time, TimeUnit timeUnit, Runnable runnable) {
//        executor.schedule(runnable, time, timeUnit);
//    }
//
//    @Slf4j
//    public static class TaskExceptionHandler implements Thread.UncaughtExceptionHandler {
//        @Override
//        public void uncaughtException(Thread t, Throwable e) {
//            log.error("Error while handling task on thread " + t.getName(), e);
//        }
//    }

    private static ScheduledExecutorService taskExecutor;
    private static ExecutorService executorService = newCachedThreadPool();

    public static void scheduleRepeatingTask(int time, TimeUnit timeUnit, Runnable runnable) {
        getTaskExecutor().scheduleAtFixedRate(runnable, 0, time, timeUnit);
    }

    public static void handleTaskOnThread(Runnable runnable) {
        executorService.submit(runnable);
    }

    public static ScheduledExecutorService getTaskExecutor() {
        if (taskExecutor == null) {
            taskExecutor = Executors.newScheduledThreadPool(Math.max(1, Runtime.getRuntime().availableProcessors() - 1), new ThreadFactoryBuilder().setNameFormat("scheduler-%d").setUncaughtExceptionHandler(new TaskExceptionHandler()).build());
        }
        return taskExecutor;
    }

    public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 1, TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadFactoryBuilder().setNameFormat("task-%d").setUncaughtExceptionHandler(new TaskExceptionHandler()).build());
    }

    @Slf4j
    public static class TaskExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            log.error("Error while running task", e);
            e.printStackTrace();
        }
    }
}

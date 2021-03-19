package com.unrealdinnerbone.unreallib;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class TaskScheduler {

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

    public static class TaskExceptionHandler implements Thread.UncaughtExceptionHandler {

        public static Logger LOGGER = LoggerFactory.getLogger(TaskExceptionHandler.class);

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            LOGGER.error(e.getMessage());
        }
    }
}

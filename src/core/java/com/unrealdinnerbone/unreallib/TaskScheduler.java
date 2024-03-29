package com.unrealdinnerbone.unreallib;

import com.unrealdinnerbone.unreallib.exception.ExceptionConsumer;
import com.unrealdinnerbone.unreallib.exception.ExceptionRunnable;
import com.unrealdinnerbone.unreallib.exception.ExceptionSuppler;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class TaskScheduler {

    private static final Timer TIMER = new Timer();

    public static TimerTask scheduleRepeatingTask(int time, TimeUnit timeUnit, TimerTask task) {
        TIMER.scheduleAtFixedRate(task, 0, timeUnit.toMillis(time));
        return task;
    }

    public static TimerTask scheduleTask(Instant timeToRun, Consumer<TimerTask> task) {
        TimerTask timerTask = createTask(task);
        TIMER.schedule(timerTask, Date.from(timeToRun));
        return timerTask;
    }

    public static TimerTask scheduleTask(int time, TimeUnit timeUnit, Consumer<TimerTask> task) {
        TimerTask timerTask = createTask(task);
        TIMER.schedule(timerTask, timeUnit.toMillis(time));
        return timerTask;
    }
    public static TimerTask scheduleRepeatingTask(int time, TimeUnit timeUnit, Consumer<TimerTask> task) {
        return scheduleRepeatingTask(time, timeUnit, createTask(task));
    }

    public static TimerTask scheduleRepeatingTaskExpectantly(int time, TimeUnit timeUnit, ExceptionConsumer<TimerTask, Exception> task, Consumer<Exception> consumer) {
        return scheduleRepeatingTask(time, timeUnit, new TimerTask() {
            @Override
            public void run() {
                try {
                    task.accept(this);
                }catch(Exception e) {
                    consumer.accept(e);
                }
            }
        });
    }

    public static <T extends Exception> CompletableFuture<Void> runAsync(ExceptionRunnable<T> runnable) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        handleTaskOnThread(() -> {
            try {
                runnable.run();
                future.complete(null);
            }catch(Exception e) {
                future.completeExceptionally(e.getCause() != null ? e.getCause() : e);
            }
        });
        return future;
    }

    public static <T, E extends Exception> CompletableFuture<T> getAsync(ExceptionSuppler<T, E> runnable, Executor executor) {
        CompletableFuture<T> future = new CompletableFuture<>();
        handleTaskOnThread(() -> {
            try {
                future.complete(runnable.get());
            }catch(Exception e) {
                future.completeExceptionally(e);
            }
        }, executor);
        return future;
    }

    public static <T extends Exception> CompletableFuture<Void> runAsync(ExceptionRunnable<T> runnable, Executor executor) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        handleTaskOnThread(() -> {
            try {
                runnable.run();
                future.complete(null);
            }catch(Exception e) {
                future.completeExceptionally(e);
            }
        }, executor);
        return future;
    }


    public static <T> CompletableFuture<Void> allAsync(List<CompletableFuture<T>> futures) {
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }
    public static void handleTaskOnThread(Runnable runnable) {
        CompletableFuture.runAsync(runnable);
    }

    public static void handleTaskOnThread(Runnable runnable, Executor executor) {
        CompletableFuture.runAsync(runnable, executor);
    }

    public static Timer getTimer() {
        return TIMER;
    }

    private static TimerTask createTask(Consumer<TimerTask> task) {
        return new TimerTask() {
            @Override
            public void run() {
                task.accept(this);
            }
        };
    }

}

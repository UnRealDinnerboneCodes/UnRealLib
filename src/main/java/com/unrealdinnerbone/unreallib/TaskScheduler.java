package com.unrealdinnerbone.unreallib;

import java.time.Instant;
import java.util.Date;
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

    public static void scheduleTask(Instant timeToRun, Task task) {
        TIMER.schedule(new TimerTask() {
            @Override
            public void run() {
                task.run(this);
            }
        }, Date.from(timeToRun));
    }
    public static TimerTask scheduleRepeatingTask(int time, TimeUnit timeUnit, Task task) {
        return scheduleRepeatingTask(time, timeUnit, new TimerTask() {
            @Override
            public void run() {
                task.run(this);
            }
        });
    }

    public static TimerTask scheduleRepeatingTaskExpectantly(int time, TimeUnit timeUnit, ExceptionTask task, Consumer<Exception> consumer) {
        return scheduleRepeatingTask(time, timeUnit, new TimerTask() {
            @Override
            public void run() {
                try {
                    task.run(this);
                }catch(Exception e) {
                    consumer.accept(e);
                }
            }
        });
    }

    public static void handleTaskOnThread(Runnable runnable) {
        CompletableFuture.runAsync(runnable);
    }

    public static Timer getTimer() {
        return TIMER;
    }

    public interface Task {
        void run(TimerTask task);
    }

    public interface ExceptionTask {
        void run(TimerTask task) throws Exception;
    }

}

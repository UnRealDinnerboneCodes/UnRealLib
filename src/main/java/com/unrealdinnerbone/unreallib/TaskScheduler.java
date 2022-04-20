package com.unrealdinnerbone.unreallib;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

public class TaskScheduler {

    private static final Timer TIMER = new Timer();

    public static TimerTask scheduleRepeatingTask(int time, TimeUnit timeUnit, TimerTask task) {
        TIMER.scheduleAtFixedRate(task, 0, timeUnit.toMillis(time));
        return task;
    }

    public static void handleTaskOnThread(Runnable runnable) {
        CompletableFuture.runAsync(runnable);
    }

    public static Timer getTimer() {
        return TIMER;
    }

}

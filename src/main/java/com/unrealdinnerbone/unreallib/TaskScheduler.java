package com.unrealdinnerbone.unreallib;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

public class TaskScheduler {

    private static final Timer TIMER = new Timer();

    public static TimerTask scheduleRepeatingTask(int time, TimeUnit timeUnit, Runnable runnable) {
        TimerTask timerTask = taskOf(runnable);
        TIMER.scheduleAtFixedRate(timerTask, 0, timeUnit.toMillis(time));
        return timerTask;
    }

    public static void handleTaskOnThread(Runnable runnable) {
        CompletableFuture.runAsync(runnable);
    }

    public static Timer getTimer() {
        return TIMER;
    }

    public static TimerTask taskOf(Runnable runnable) {
        return new TimerTask() {
            @Override
            public void run() {
                runnable.run();
            }
        };
    }
}

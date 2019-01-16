package com.unrealdinnerbone.unreallib;


import java.util.Timer;
import java.util.TimerTask;

public class TaskScheduler {

    private static final Timer timer = new Timer();


    public static void scheduleAtFixedRate(int time, Runnable runnable) {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runnable.run();
            }
        }, 0, time);
    }
}

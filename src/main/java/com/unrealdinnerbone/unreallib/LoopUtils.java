package com.unrealdinnerbone.unreallib;

public class LoopUtils {

    public static void loop(ExceptionSuppler<Boolean> next, ExceptionRunnable onGood) {
        try {
            while (next.get()) {
                onGood.run();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

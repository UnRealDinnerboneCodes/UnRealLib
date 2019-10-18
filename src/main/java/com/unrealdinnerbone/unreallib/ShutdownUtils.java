package com.unrealdinnerbone.unreallib;

public class ShutdownUtils {
    private static int count = 1;

    public static void addShutdownHook(Thread thread) {
        Runtime.getRuntime().addShutdownHook(thread);
    }

    public static void addShutdownHook(Runnable runnable) {
        addShutdownHook(new Thread(runnable, getName()));
    }

    private static String getName() {
        return "shutdown-" + count++;
    }
}

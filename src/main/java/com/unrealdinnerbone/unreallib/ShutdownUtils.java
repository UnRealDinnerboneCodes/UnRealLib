package com.unrealdinnerbone.unreallib;

public class ShutdownUtils {
    private static int count = 1;

    public static Thread addShutdownHook(Runnable runnable) {
        Thread thread = new Thread(runnable, getName());
        Runtime.getRuntime().addShutdownHook(thread);
        return thread;
    }

    private static String getName() {
        return "shutdown-" + count++;
    }

    public static void shutdown() {
        Runtime.getRuntime().exit(0);
    }
}

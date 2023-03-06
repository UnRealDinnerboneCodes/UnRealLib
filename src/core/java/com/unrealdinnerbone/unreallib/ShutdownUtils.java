package com.unrealdinnerbone.unreallib;

import org.jetbrains.annotations.NotNull;

public class ShutdownUtils {
    private static int count = 1;

    @NotNull
    public static Thread addShutdownHook(Runnable runnable) {
        Thread thread = new Thread(runnable, getName());
        Runtime.getRuntime().addShutdownHook(thread);
        return thread;
    }

    @NotNull
    private static String getName() {
        return "shutdown-" + count++;
    }

    public static void shutdown() {
        Runtime.getRuntime().exit(0);
    }
}

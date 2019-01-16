package com.unrealdinnerbone.unreallib;

public class ShutdownUtils
{
    public static void addShutdownHook(Thread thread) {
        Runtime.getRuntime().addShutdownHook(thread);
    }
    public static void addShutdownHook(Runnable runnable) {
        addShutdownHook(new Thread(runnable));
    }
}

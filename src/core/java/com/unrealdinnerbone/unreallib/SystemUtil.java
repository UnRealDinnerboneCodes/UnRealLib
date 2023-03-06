package com.unrealdinnerbone.unreallib;

public class SystemUtil {
    private static final long MEGABYTE = (long) Math.pow(1024, 2);
    public static long getUsedMemory() {
        return Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory() / MEGABYTE;
    }
    public static long getMaxMemory() {
        return Runtime.getRuntime().maxMemory() / MEGABYTE;
    }
}

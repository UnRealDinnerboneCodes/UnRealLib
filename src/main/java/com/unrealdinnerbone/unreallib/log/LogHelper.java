package com.unrealdinnerbone.unreallib.log;


import com.unrealdinnerbone.unreallib.api.ILogger;
import com.unrealdinnerbone.unreallib.impl.JavaLogger;

import java.util.function.Function;

public class LogHelper
{
    private static LogLevel logLevel = LogLevel.INFO;

    public static ILogger getLogger(String name, Function<String, String> message) {
        return new JavaLogger(name, message);
    }

    public static ILogger getLogger(String name) {
        return getLogger(name, null);
    }

    public static ILogger getLogger(Class clazz) {
        return getLogger(clazz.getSimpleName());
    }

    public static ILogger getLogger(Class clazz,  Function<String, String> message) {
        return getLogger(clazz.getSimpleName(), message);
    }

    public static void setLogLevel(LogLevel logLevel) {
        LogHelper.logLevel = logLevel;
    }

    public static LogLevel getLogLevel() {
        return logLevel;
    }

    public enum LogLevel {
        INFO,
        DEBUG
    }
}

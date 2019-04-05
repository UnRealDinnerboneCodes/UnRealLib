package com.unrealdinnerbone.unreallib.log;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LogHelper
{
    @Getter @Setter
    private static Level logMasterLevel = Level.INFO;
    private static List<Logger> loggers = new ArrayList<>();

    public static Logger getLogger(String name, Function<LogRecord, String> logHandler) {
        Logger logger = Logger.getLogger(name);
        logger.setUseParentHandlers(false);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new FormatterConsole(name,logHandler));
        consoleHandler.setLevel(logMasterLevel);
        logger.addHandler(consoleHandler);
        loggers.add(logger);
        return logger;
    }

    public static Logger getLogger(String name) {
        return getLogger(name, null);
    }

    public static Logger getLogger(Class clazz) {
        return getLogger(clazz.getSimpleName());
    }

    public static Logger getLogger(Class clazz, Function<LogRecord, String> logHandler) {
        return getLogger(clazz.getSimpleName(), logHandler);
    }

    //Todo This better
    public static void logExpection(Logger logger, Exception e) {
        e.printStackTrace();
    }

    public static void setLogLevel(Level logLevel) {
        loggers.forEach(logger -> logger.setLevel(logLevel));
    }

}

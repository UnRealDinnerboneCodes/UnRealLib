package com.unrealdinnerbone.unreallib.impl;

import com.unrealdinnerbone.unreallib.DateFormatter;
import com.unrealdinnerbone.unreallib.StringUtils;
import com.unrealdinnerbone.unreallib.api.ILogger;
import com.unrealdinnerbone.unreallib.log.FormatterConsole;
import com.unrealdinnerbone.unreallib.log.LogHelper;

import java.util.function.Function;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class JavaLogger implements ILogger {

    private final Logger loggger;

    public JavaLogger(String name, Function<String, String> message) {
        this.loggger =  Logger.getLogger(name);
        this.loggger .setUseParentHandlers(false);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new FormatterConsole(name, createHandler(message, name)));
        consoleHandler.setLevel(Level.ALL);
        this.loggger.addHandler(consoleHandler);
    }

    private Function<LogRecord, String> createHandler(Function<String, String> message, String name) {
        return logRecord -> message == null ? StringUtils.replace("[{4}] {0} [{1}]: {2}{3}", DateFormatter.getLoggerDate(), name, logRecord.getMessage(), System.lineSeparator(), logRecord.getLevel().getName()) : message.apply(logRecord.getMessage());
    }

    @Override
    public void info(String message, Object... replacements) {
        loggger.log(Level.INFO, StringUtils.replace(message, replacements));
    }

    @Override
    public void debug(String message, Object... replacements) {
        if(LogHelper.getLogLevel() == LogHelper.LogLevel.DEBUG) {
            loggger.log(Level.FINE, StringUtils.replace(message, replacements));
        }
    }

    @Override
    public void error(String message, Object... replacements) {
        loggger.log(Level.SEVERE, StringUtils.replace(message, replacements));
    }

    @Override
    public void error(String message, Throwable error) {
        loggger.log(Level.SEVERE, message, error);
    }

    @Override
    public void error(String message, Throwable error, Object... replacements) {
        loggger.log(Level.SEVERE, StringUtils.replace(message, replacements), error);
    }

}

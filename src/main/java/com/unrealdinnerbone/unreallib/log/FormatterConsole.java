package com.unrealdinnerbone.unreallib.log;

import com.unrealdinnerbone.unreallib.DateFormatter;
import com.unrealdinnerbone.unreallib.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.function.Function;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import static com.unrealdinnerbone.unreallib.StringUtils.NEW_LINE;
import static com.unrealdinnerbone.unreallib.StringUtils.TAB;

@Slf4j
public class FormatterConsole extends Formatter {

    private Function<LogRecord, String> logHandler;

    public FormatterConsole(Function<LogRecord, String> logHandler) {
        this.logHandler = logHandler == null ? (logRecord -> {
            String message = StringUtils.replace("{5}[{7}] [{4}] {0} [{1}]: {2}{3}{6}", DateFormatter.getLoggerDate(), logRecord.getLoggerName(), logRecord.getMessage(), System.lineSeparator(), logRecord.getLevel().getName(), Codes.fromLogLevel(logRecord.getLevel()), Codes.RESET.getType(), Thread.currentThread().getName());
            if(logRecord.getThrown() != null) {
               message += getLoggableString(logRecord.getThrown());
            }
            return message;
        }) : logHandler;
    }

    public static String getLoggableString(Throwable throwable) {
        StringBuilder builder = new StringBuilder();
        if(throwable.getCause() != null) {
            builder.append(TAB).append(throwable.getCause()).append(NEW_LINE);
        }
        Arrays.stream(throwable.getStackTrace()).forEach(stackTraceElement -> builder.append(TAB).append(stackTraceElement.toString()).append(NEW_LINE));
        return builder.toString();
    }

    public FormatterConsole() {
        this(null);
    }

    @Override
    public String format(LogRecord record) {
        return logHandler.apply(record);
    }

    @AllArgsConstructor
    @Getter
    public enum Codes {

        RESET("\u001B[0m"),
        SEVERE("\u001b[91m"),
        WARNING("\u001b[93m"),
        INFO("\u001b[32m"),
        CONFIG("\u001b[94m"),
        FINE("\u001b[36m"),
        FINER("\u001b[35m"),
        FINEST("\u001b[90m");

        private String type;

        private final static Codes[] CODES = values();

        public static String fromLogLevel(Level level) {
            return Arrays.stream(CODES).filter(codes -> codes.getType().equalsIgnoreCase(level.getName())).findFirst().orElse(SEVERE).getType();
        }
    }
}
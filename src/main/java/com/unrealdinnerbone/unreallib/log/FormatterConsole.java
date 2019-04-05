package com.unrealdinnerbone.unreallib.log;

import com.unrealdinnerbone.unreallib.DateFormatter;
import com.unrealdinnerbone.unreallib.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class FormatterConsole extends Formatter {

    private final String NAME;
    private Function<LogRecord, String> logHandler;


    public FormatterConsole(String name, Function<LogRecord, String> logHandler) {
        this.NAME = name;
        this.logHandler = logHandler == null ? (logRecord -> StringUtils.replace("[{4}] {0} [{1}]: {2}{3}", DateFormatter.getLoggerDate(), NAME, logRecord.getMessage(), System.lineSeparator(), logRecord.getLevel().getName())) : logHandler;
    }

    @Override
    public String format(LogRecord record) {
        return logHandler.apply(record);
    }
}
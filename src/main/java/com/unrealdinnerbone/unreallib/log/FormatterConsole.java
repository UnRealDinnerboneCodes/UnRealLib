package com.unrealdinnerbone.unreallib.log;

import com.unrealdinnerbone.unreallib.DateFormatter;
import com.unrealdinnerbone.unreallib.StringUtils;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class FormatterConsole extends Formatter {

    private final String NAME;

    public FormatterConsole(String name) {
        this.NAME = name;
    }

    @Override
    public String format(LogRecord record) {
        return StringUtils.replace("[{4}] {0} [{1}]: {2}{3}", DateFormatter.getLoggerDate(), NAME, record.getMessage(), System.lineSeparator(), record.getLevel().getName());
    }
}
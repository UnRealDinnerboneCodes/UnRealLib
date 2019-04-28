package com.unrealdinnerbone.unreallib.api;

public interface ILogger
{
    void info(String message, Object... replacements);

    void debug(String message, Object... replacements);

    void error(String message, Object... replacements);

    void error(String message, Throwable error);

    void error(String message, Throwable error, Object... replacements);

}

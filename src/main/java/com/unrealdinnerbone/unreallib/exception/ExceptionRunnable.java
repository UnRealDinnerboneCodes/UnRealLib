package com.unrealdinnerbone.unreallib.exception;

public interface ExceptionRunnable<E extends Exception> {
    void run() throws E;
}

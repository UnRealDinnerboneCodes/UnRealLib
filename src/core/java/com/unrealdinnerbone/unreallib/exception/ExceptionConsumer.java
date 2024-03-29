package com.unrealdinnerbone.unreallib.exception;

public interface ExceptionConsumer<T, E extends Exception> {
    void accept(T t) throws E;
}

package com.unrealdinnerbone.unreallib.exception;

public interface ExceptionSuppler<T, E extends Exception> {
    T get() throws E;
}

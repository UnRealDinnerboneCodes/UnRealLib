package com.unrealdinnerbone.unreallib.exception;

public interface ExceptionFunction<E extends Exception, T, B> {
    T get(B b) throws E;
}

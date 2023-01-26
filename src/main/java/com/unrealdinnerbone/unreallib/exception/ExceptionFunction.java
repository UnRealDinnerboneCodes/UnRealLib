package com.unrealdinnerbone.unreallib.exception;

public interface ExceptionFunction<E extends Exception, B, T> {
    T apply(B b) throws E;

    default <R> ExceptionFunction<E, B, R> andThen(ExceptionFunction<E, T, R> after) {
        return (B b) -> after.apply(apply(b));
    }

}

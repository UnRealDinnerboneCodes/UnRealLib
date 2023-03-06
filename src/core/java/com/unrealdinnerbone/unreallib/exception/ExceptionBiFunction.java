package com.unrealdinnerbone.unreallib.exception;

public interface ExceptionBiFunction<E extends Exception, T, A, B> {
    T get(A a, B b) throws E;
}

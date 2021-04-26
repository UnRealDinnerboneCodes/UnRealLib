package com.unrealdinnerbone.unreallib;

public interface ExceptionSuppler<T, E extends Exception> {
    T get() throws E;
}

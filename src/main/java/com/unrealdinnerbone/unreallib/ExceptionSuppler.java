package com.unrealdinnerbone.unreallib;

public interface ExceptionSuppler<T> {
    T get() throws Exception;
}

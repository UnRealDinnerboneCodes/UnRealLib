package com.unrealdinnerbone.unreallib;

public interface ExceptionConsumer<T, E extends Exception>
{
    void accept(T t) throws E;
}

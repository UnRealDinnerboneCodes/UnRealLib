package com.unrealdinnerbone.unreallib.apiutils;

import org.jetbrains.annotations.Nullable;

public record DirectResult<T>(T value) implements IReturnResult<T>{

    @Override
    public @Nullable T get() {
        return value;
    }

    @Override
    public T getExceptionally() {
        return value;
    }
}

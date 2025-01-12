package com.unrealdinnerbone.unreallib.exception;

import java.util.Objects;
import java.util.function.Function;

public interface ExceptionBiFunction<E extends Exception, T, U, R> {
    R apply(T t, U u) throws E;

    default <V> ExceptionBiFunction<E, T, U, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t, U u) -> after.apply(apply(t, u));
    }
}


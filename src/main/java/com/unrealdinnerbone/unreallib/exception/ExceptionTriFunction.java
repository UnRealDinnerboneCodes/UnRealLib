package com.unrealdinnerbone.unreallib.exception;

import java.util.Objects;
import java.util.function.Function;

public interface ExceptionTriFunction<E extends Exception, T, U, V, R> {
    R apply(T var1, U var2, V var3) throws E;

    default <W> ExceptionTriFunction<E, T, U, V, W> andThen(Function<? super R, ? extends W> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.apply(this.apply(t, u, v));
    }
}

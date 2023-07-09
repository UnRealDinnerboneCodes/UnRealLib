package com.unrealdinnerbone.unreallib;

import com.unrealdinnerbone.unreallib.exception.ExceptionSuppler;

import java.util.Optional;

public record ErrorableValue<E extends Exception, T>(T value, E exception) implements ExceptionSuppler<T, E> {

    public boolean hasException() {
        return exception != null;
    }

    public Optional<T> asOptional() {
        return Optional.ofNullable(value);
    }

    public T get() throws E {
        if(exception != null) {
            throw exception;
        }
        return value;
    }

    public static <E extends Exception, T> ErrorableValue<E, T> of(T value) {
        return new ErrorableValue<>(value, null);
    }

    public static <E extends Exception, T> ErrorableValue<E, T> of(E exception) {
        return new ErrorableValue<>(null, exception);
    }

    public static <E extends Exception, T> ErrorableValue<E, T> of(T value, E exception) {
        return new ErrorableValue<>(value, exception);
    }
}

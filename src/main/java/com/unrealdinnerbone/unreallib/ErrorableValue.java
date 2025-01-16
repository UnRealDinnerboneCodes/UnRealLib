package com.unrealdinnerbone.unreallib;

import com.unrealdinnerbone.unreallib.either.Either;
import com.unrealdinnerbone.unreallib.exception.ExceptionSuppler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ErrorableValue<E extends Exception, T> extends Either<E, T> implements ExceptionSuppler<T, E> {

    @NotNull
    public static <E extends Exception, T> ErrorableValue<E, T> error(@NotNull E value) {
        return new ErrorableValue<>(new StoredValue<>(value), null);
    }

    @NotNull
    public static <E extends Exception, T> ErrorableValue<E, T> value(@Nullable T value) {
        return new ErrorableValue<>(null, new StoredValue<>(value));
    }

    @NotNull
    public static <E extends Exception, T> ErrorableValue<E, T> of(T value) {
        return value(value);
    }

    @NotNull
    public static <E extends Exception, T> ErrorableValue<E, T> of(E exception) {
        return error(exception);
    }

    protected ErrorableValue(StoredValue<E> exception, StoredValue<T> value) {
        super(exception, value);
    }

    public boolean hasException() {
        return left != null;
    }

    public Optional<T> asOptional() {
        return right != null ? Optional.ofNullable(right.value()) : Optional.empty();
    }

    public T get() throws E {
        if (left != null) {
            throw left.value();
        }
        return right.value();
    }

    public void ifPresentOrElse(Consumer<? super T> action, Consumer<E> errorAction) {
        if (right != null) {
            action.accept(right.value());
        } else {
            errorAction.accept(left.value());
        }
    }

    public T getAndRun(Supplier<T> supplier, Consumer<E> errorAction) {
        if (right != null) {
            return right.value();
        } else {
            errorAction.accept(left.value());
            return supplier.get();
        }
    }

    public T value() {
        return right == null ? null : right.value();
    }

    public E exception() {
        return left == null ? null : left.value();
    }


}

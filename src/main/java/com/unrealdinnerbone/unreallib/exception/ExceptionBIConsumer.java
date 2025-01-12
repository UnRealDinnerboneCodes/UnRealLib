package com.unrealdinnerbone.unreallib.exception;

public interface ExceptionBIConsumer<T, U, E extends Exception> {
    void accept(T t, U u) throws E;

    default ExceptionBIConsumer<T, U, E> andThen(ExceptionBIConsumer<? super T, ? super U, ? extends E> after) {
        return (l, r) -> {
            accept(l, r);
            after.accept(l, r);
        };
    }
}

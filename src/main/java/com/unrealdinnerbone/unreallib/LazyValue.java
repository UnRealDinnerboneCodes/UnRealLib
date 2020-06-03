package com.unrealdinnerbone.unreallib;

import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class LazyValue<T> {
    private final Supplier<T> tSupplier;
    private T t;

    public T getT() {
        if(t == null) {
            t = tSupplier.get();
        }
        return t;
    }
}

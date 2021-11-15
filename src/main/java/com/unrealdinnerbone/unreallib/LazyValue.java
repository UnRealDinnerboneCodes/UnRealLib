package com.unrealdinnerbone.unreallib;


import java.util.function.Supplier;

public class LazyValue<T> {
    private final Supplier<T> tSupplier;
    private T t;

    public LazyValue(Supplier<T> tSupplier) {
        this.tSupplier = tSupplier;
    }

    public T get() {
        if(t == null) {
            t = tSupplier.get();
        }
        return t;
    }
}

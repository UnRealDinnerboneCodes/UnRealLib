package com.unrealdinnerbone.unreallib;

import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public record StoredValue<T>(T value) {

    public <N> StoredValue<N> map(@NotNull Function<? super T, ? extends N> func) {
        return new StoredValue<>(func.apply(value));
    }
}

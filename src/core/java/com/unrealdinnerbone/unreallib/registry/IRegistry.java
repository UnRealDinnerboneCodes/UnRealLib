package com.unrealdinnerbone.unreallib.registry;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface IRegistry<T> extends Iterable<T> {
    List<T> getValues();

    Optional<T> get(String name);

    T register(T value);

    T createNonRegistryValue(String name);

    T register(String value);

    String toString(T value);

    @NotNull
    @Override
    default Iterator<T> iterator() {
        return getValues().iterator();
    }
}

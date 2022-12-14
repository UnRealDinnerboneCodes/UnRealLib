package com.unrealdinnerbone.unreallib.json.api;

import java.util.List;
import java.util.Optional;

public interface IRegistry<T> {
    List<T> getValues();

    Optional<T> get(String name);

    T register(T value);

    T createNonRegistryValue(String name);

    T register(String value);

    String toString(T value);
}

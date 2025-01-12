package com.unrealdinnerbone.unreallib.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class BasicRegistry<T> implements IRegistry<T> {

    private final List<T> registry = new ArrayList<>();
    private final Function<T, String> function;
    private final BiFunction<String, Boolean, T> creator;

    public BasicRegistry(BiFunction<String, Boolean, T> creator, Function<T, String> function) {
        this.creator = creator;
        this.function = function;
    }

    @Override
    public List<T> getValues() {
        return registry;
    }

    @Override
    public Optional<T> get(String name) {
        for (T value : registry) {
            if(toString(value).equals(name)) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }

    @Override
    public T register(T value) {
        registry.add(value);
        return value;
    }

    @Override
    public T createNonRegistryValue(String name) {
        return creator.apply(name, false);
    }

    @Override
    public T register(String value) {
        return register(creator.apply(value, true));
    }


    @Override
    public String toString(T value) {
        return function.apply(value);
    }
}

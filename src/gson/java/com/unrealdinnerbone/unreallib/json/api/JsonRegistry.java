package com.unrealdinnerbone.unreallib.json.api;

import com.unrealdinnerbone.unreallib.registry.BasicRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class JsonRegistry<T> extends BasicRegistry<T> {

    public static final Map<Class<?>, JsonRegistry<?>> REGISTRIES = new HashMap<>();

    private final boolean allowJsonCreation;
    public JsonRegistry(BiFunction<String, Boolean, T> creator, Function<T, String> function, Class<T> type, boolean allowJsonCreation) {
        super(creator, function);
        this.allowJsonCreation = allowJsonCreation;
        REGISTRIES.put(type, this);
    }

    public static <T extends INamed> JsonRegistry<T> of(Class<T> type, Function<String, T> creator) {
        return new JsonRegistry<>((s, aBoolean) -> creator.apply(s), INamed::name, type, false);
    }

    public static JsonRegistry<?> findForType(Class<?> clazz) {
        return REGISTRIES.get(clazz);
    }

    public boolean allowJsonCreation() {
        return allowJsonCreation;
    }
}

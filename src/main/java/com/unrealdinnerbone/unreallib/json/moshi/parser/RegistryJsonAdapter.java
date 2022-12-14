package com.unrealdinnerbone.unreallib.json.moshi.parser;

import com.squareup.moshi.*;
import com.unrealdinnerbone.unreallib.json.api.IRegistry;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Optional;


public class RegistryJsonAdapter<B, T extends IRegistry<B>> extends JsonAdapter<B> {
    private final T registry;
    private final boolean createIfNotExists;

    public static <B, T extends IRegistry<B>> RegistryJsonAdapter<B, T> create(T registry, boolean createIfNotExists) {
        return new RegistryJsonAdapter<>(registry, createIfNotExists);
    }

    public RegistryJsonAdapter(T registry, boolean createIfNotExists) {
        this.registry = registry;
        this.createIfNotExists = createIfNotExists;
    }

    @Override
    @FromJson
    public @NotNull B fromJson(JsonReader reader) throws IOException {
        String id = reader.nextString();
        Optional<B> optional = registry.get(id);
        return createIfNotExists ? optional.orElseGet(() -> registry.createNonRegistryValue(id)) : optional.orElseThrow(() -> new JsonDataException("Could not find " + id + " in " + registry.getClass().getSimpleName()));
    }

    @Override
    @ToJson
    public void toJson(JsonWriter writer, B value) throws IOException {
        writer.value(registry.toString(value));
    }

}
package com.unrealdinnerbone.unreallib.json.gson.parsers.basic;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.unrealdinnerbone.unreallib.LogHelper;
import com.unrealdinnerbone.unreallib.json.api.JsonRegistry;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Optional;


public class RegistryJsonAdapter<B, T extends JsonRegistry<B>> extends TypeAdapter<B> {

    private static final Logger LOGGER = LogHelper.getLogger();
    private final T registry;
    public RegistryJsonAdapter(T registry) {
        this.registry = registry;
    }

    public static RegistryJsonAdapter create(JsonRegistry<?> registry) {
        return new RegistryJsonAdapter<>(registry);
    }

    @Override
    public void write(JsonWriter out, B value) throws IOException {
        if(value != null) {
            out.value(registry.toString(value));
        }else {
            out.nullValue();
        }
    }

    @Override
    public B read(JsonReader in) throws IOException {
        String id = in.nextString();
        Optional<B> optional = registry.get(id);
        return registry.allowJsonCreation() ? optional.orElseGet(() -> {
            LOGGER.warn("Could not find {} in {}, creating a new one", id, registry.getClass().getSimpleName());
            return registry.createNonRegistryValue(id);
        }) : optional.orElseThrow(() -> new IOException("Could not find " + id + " in " + registry.getClass().getSimpleName()));
    }
}
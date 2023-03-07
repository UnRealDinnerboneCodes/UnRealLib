package com.unrealdinnerbone.unreallib.json.moshi.parser;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.unrealdinnerbone.unreallib.json.api.IJsonParser;
import com.unrealdinnerbone.unreallib.json.api.IHasClazz;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public abstract class InterfaceSerializer extends JsonAdapter<IHasClazz<?>> {

    @Nullable
    @Override
    public IHasClazz<?> fromJson(JsonReader reader) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void toJson(JsonWriter writer, @Nullable IHasClazz value) throws IOException {
        if(value.getClazz() == null) {
            throw new IOException("Clazz is null for " + value.getClass().getName());
        }
        writer.jsonValue(getJsonParser().toJsonObject(value.getClazz(), value));
    }

    public abstract IJsonParser getJsonParser();
}
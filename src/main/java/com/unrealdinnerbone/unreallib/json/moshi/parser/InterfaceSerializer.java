package com.unrealdinnerbone.unreallib.json.moshi.parser;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.unrealdinnerbone.unreallib.json.IJsonParser;
import com.unrealdinnerbone.unreallib.json.moshi.IHasClazz;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class InterfaceSerializer extends JsonAdapter<IHasClazz<?>> {

    private IJsonParser<?> iJsonParser;

    public InterfaceSerializer(IJsonParser<?> iJsonParser) {
        this.iJsonParser = iJsonParser;
    }

    @Nullable
    @Override
    public IHasClazz<?> fromJson(JsonReader reader) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void toJson(JsonWriter writer, @Nullable IHasClazz value) throws IOException {
        writer.jsonValue(iJsonParser.toJsonObject(value.getClazz(), value));
    }

}
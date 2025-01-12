package com.unrealdinnerbone.unreallib.json.gson.api;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.unrealdinnerbone.unreallib.exception.ExceptionBIConsumer;
import com.unrealdinnerbone.unreallib.exception.ExceptionFunction;

import java.io.IOException;

public class FunctionTypeAdapter<T> extends TypeAdapter<T> {

    private final ExceptionBIConsumer<JsonWriter, T, IOException> write;
    private final ExceptionFunction<IOException, JsonReader, T> read;

    public FunctionTypeAdapter(ExceptionBIConsumer<JsonWriter, T, IOException> write, ExceptionFunction<IOException, JsonReader, T> read) {
        this.write = write;
        this.read = read;
    }

    @Override
    public void write(JsonWriter out, T value) throws IOException {
        write.accept(out, value);
    }

    @Override
    public T read(JsonReader in) throws IOException {
        return read.apply(in);
    }
}

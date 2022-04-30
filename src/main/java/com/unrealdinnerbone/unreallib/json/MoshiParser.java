package com.unrealdinnerbone.unreallib.json;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.Moshi;
import com.unrealdinnerbone.unreallib.json.moshi.RawJsonAdapter;
import com.unrealdinnerbone.unreallib.json.moshi.parser.UUIDFactory;
import com.unrealdinnerbone.unreallib.json.temp.RecordsJsonAdapterFactory;
import okio.BufferedSource;

import java.io.IOException;

public record MoshiParser(Moshi moshi) implements IJsonParser<IOException> {

    public static final MoshiParser INSTANCE = new MoshiParser(new Moshi.Builder()
            .add(new RawJsonAdapter())
            .add(new RecordsJsonAdapterFactory())
            .build());

    @Override
    public <T> T parse(Class<T> tClass, String value) throws IOException {
        return moshi.adapter(tClass).fromJson(value);
    }

    @Override
    public <T> String toJson(Class<T> tClass, T value) {
        return moshi.adapter(tClass).lenient().toJson(value);
    }

    @Override
    public <T> String toFancyJson(Class<T> tClass, T value) {
        return moshi.adapter(tClass).indent("    ").toJson(value);
    }

    public <T> T parse(Class<T> tClass, JsonReader value) throws IOException {
        return moshi.adapter(tClass).fromJson(value);
    }

    public <T> T parse(Class<T> tClass, BufferedSource value) throws IOException {
        return moshi.adapter(tClass).fromJson(value);
    }

}

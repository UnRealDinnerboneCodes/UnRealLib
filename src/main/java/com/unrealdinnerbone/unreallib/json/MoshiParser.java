package com.unrealdinnerbone.unreallib.json;

import com.squareup.moshi.Moshi;
import dev.zacsweers.moshix.records.RecordsJsonAdapterFactory;

import java.io.IOException;

public class MoshiParser implements IJsonParser<IOException> {

    private static final Moshi MOSHI = new Moshi.Builder().add(new RecordsJsonAdapterFactory()).build();
    public static final MoshiParser INSTANCE = new MoshiParser();

    protected MoshiParser() {}

    @Override
    public <T> T parse(Class<T> tClass, String value) throws IOException {
        return MOSHI.adapter(tClass).fromJson(value);
    }

    @Override
    public <T> String toJson(Class<T> tClass, T value) {
        return MOSHI.adapter(tClass).lenient().toJson(value);
    }
}

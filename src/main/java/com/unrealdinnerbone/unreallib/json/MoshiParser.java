package com.unrealdinnerbone.unreallib.json;

import com.squareup.moshi.Moshi;
import com.unrealdinnerbone.unreallib.json.moshi.UUIDFactory;
import dev.zacsweers.moshix.records.RecordsJsonAdapterFactory;

import java.io.IOException;
import java.util.UUID;

public class MoshiParser implements IJsonParser<IOException> {

    private static final Moshi MOSHI = new Moshi.Builder()
            .add(new RecordsJsonAdapterFactory())
            .add(new UUIDFactory())
            .build();
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

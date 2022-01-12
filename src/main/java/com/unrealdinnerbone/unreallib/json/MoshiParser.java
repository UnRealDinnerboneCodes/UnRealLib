package com.unrealdinnerbone.unreallib.json;

import com.squareup.moshi.Moshi;
import com.unrealdinnerbone.unreallib.json.moshi.parser.UUIDFactory;

import java.io.IOException;

public record MoshiParser(Moshi moshi) implements IJsonParser<IOException> {

    public static final MoshiParser INSTANCE = new MoshiParser(new Moshi.Builder().add(new UUIDFactory()).build());

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

}

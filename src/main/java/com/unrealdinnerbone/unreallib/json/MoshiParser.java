package com.unrealdinnerbone.unreallib.json;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.Moshi;
import com.unrealdinnerbone.unreallib.Namespace;
import com.unrealdinnerbone.unreallib.json.moshi.RawJsonAdapter;
import com.unrealdinnerbone.unreallib.json.moshi.parser.NamespaceAdapter;
import com.unrealdinnerbone.unreallib.json.moshi.parser.UUIDFactory;
import com.unrealdinnerbone.unreallib.json.temp.RecordsJsonAdapterFactory;
import okio.BufferedSource;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;

public record MoshiParser(Moshi moshi) implements IJsonParser<IOException> {

    public static final MoshiParser INSTANCE = createBasic(builder -> builder);

    public static MoshiParser createBasic(Function<Moshi.Builder, Moshi.Builder> builderConsumer) {
        return new MoshiParser(builderConsumer.apply(new Moshi.Builder()
                        .add(Namespace.class, new NamespaceAdapter())
                        .add(new RawJsonAdapter())
                        .add(new RecordsJsonAdapterFactory()))
                .build());
    }

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

    @Override
    public <T> Object toJsonObject(Class<T> tClass, T value) {
        return moshi.adapter(tClass).toJsonValue(value);
    }

    public <T> T parse(Class<T> tClass, JsonReader value) throws IOException {
        return moshi.adapter(tClass).fromJson(value);
    }

    public <T> T parse(Class<T> tClass, BufferedSource value) throws IOException {
        return moshi.adapter(tClass).fromJson(value);
    }

}

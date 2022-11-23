package com.unrealdinnerbone.unreallib.json.moshi;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.Moshi;
import com.unrealdinnerbone.unreallib.JustAString;
import com.unrealdinnerbone.unreallib.Namespace;
import com.unrealdinnerbone.unreallib.json.IJsonParser;
import com.unrealdinnerbone.unreallib.json.JsonParseException;
import com.unrealdinnerbone.unreallib.json.moshi.parser.JustAStringAdapter;
import com.unrealdinnerbone.unreallib.json.moshi.parser.NamespaceAdapter;
import com.unrealdinnerbone.unreallib.json.moshi.parser.RawJsonAdapter;
import com.unrealdinnerbone.unreallib.json.temp.RecordsJsonAdapterFactory;
import okio.BufferedSource;

import java.io.IOException;
import java.util.function.Function;

public record MoshiParser(Moshi moshi) implements IJsonParser{

    public static final MoshiParser INSTANCE = createBasic(builder -> builder);

    public static MoshiParser createBasic(Function<Moshi.Builder, Moshi.Builder> builderConsumer) {
        return new MoshiParser(builderConsumer.apply(new Moshi.Builder())
                .add(JustAString.class, new JustAStringAdapter())
                .add(Namespace.class, new NamespaceAdapter())
                .add(new RawJsonAdapter())
                .add(new RecordsJsonAdapterFactory())
                .build());
    }

    @Override
    public <T> T parse(Class<T> tClass, String value) throws JsonParseException {
        try {
            return moshi.adapter(tClass).fromJson(value);
        }catch (IOException e) {
            throw new JsonParseException(e);
        }
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

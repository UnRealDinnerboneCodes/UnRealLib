package com.unrealdinnerbone.unreallib.json.moshi;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.Moshi;
import com.unrealdinnerbone.unreallib.Namespace;
import com.unrealdinnerbone.unreallib.json.api.IJsonParser;
import com.unrealdinnerbone.unreallib.json.exception.JsonParseException;
import com.unrealdinnerbone.unreallib.json.api.JsonString;
import com.unrealdinnerbone.unreallib.json.moshi.parser.ColorAdapter;
import com.unrealdinnerbone.unreallib.json.moshi.parser.JsonStringAdapter;
import com.unrealdinnerbone.unreallib.json.moshi.parser.NamespaceAdapter;
import com.unrealdinnerbone.unreallib.json.moshi.parser.RawJsonAdapter;
import com.unrealdinnerbone.unreallib.json.temp.RecordsJsonAdapterFactory;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public record MoshiParser(Moshi moshi) implements IJsonParser{

    public static final MoshiParser INSTANCE = createBasic(builder -> builder);

    public static MoshiParser createBasic(Function<Moshi.Builder, Moshi.Builder> builderConsumer) {
        return new MoshiParser(builderConsumer.apply(new Moshi.Builder())
                .add(JsonString.class, new JsonStringAdapter())
                .add(Namespace.class, new NamespaceAdapter())
                .add(Color.class, new ColorAdapter())
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
    public <T> List<T> parseList(Class<T[]> tClass, String value) throws JsonParseException {
        return Arrays.stream(parse(tClass, value)).toList();
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

}

package com.unrealdinnerbone.unreallib.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.unrealdinnerbone.unreallib.Namespace;
import com.unrealdinnerbone.unreallib.SimpleColor;
import com.unrealdinnerbone.unreallib.json.api.IJsonParser;
import com.unrealdinnerbone.unreallib.json.exception.JsonParseException;
import com.unrealdinnerbone.unreallib.json.gson.factory.GsonWarpedFactory;
import com.unrealdinnerbone.unreallib.json.gson.parsers.basic.SimpleColorAdapter;
import com.unrealdinnerbone.unreallib.json.gson.parsers.basic.InstantAdapter;
import com.unrealdinnerbone.unreallib.json.gson.parsers.basic.NamespaceAdapter;

import java.awt.*;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class GsonParser implements IJsonParser {

    private final Gson GSON_FANCY;
    private final Gson GSON;

    public GsonParser(Function<GsonBuilder, GsonBuilder> builderConsumer) {
        GSON_FANCY = addDefaultAdapters(builderConsumer.apply(new GsonBuilder().setPrettyPrinting())).create();
        GSON = addDefaultAdapters(builderConsumer.apply(new GsonBuilder())).create();
    }


    private GsonBuilder addDefaultAdapters(GsonBuilder builder) {
        return builder
                .registerTypeAdapterFactory(new GsonWarpedFactory())
                .enableComplexMapKeySerialization()
                .registerTypeHierarchyAdapter(SimpleColor.class, new SimpleColorAdapter())
                .registerTypeAdapter(Instant.class, new InstantAdapter())
                .registerTypeAdapter(Namespace.class, new NamespaceAdapter());
    }

    @Override
    public <T> T parse(Class<T> tClass, String value) throws JsonParseException {
        try {
            return GSON.fromJson(value, tClass);
        }catch (JsonSyntaxException e) {
            throw new JsonParseException(e);
        }
    }

    @Override
    public <T> List<T> parseList(Class<T[]> tClass, String value) throws JsonParseException {
        return Arrays.stream(parse(tClass, value)).toList();
    }

    @Override
    public <T> String toJson(T value) {
        return GSON.toJson(value);
    }

    @Override
    public <T> String toFancyJson( T value) {
        return GSON_FANCY.toJson(value);
    }

    @Override
    public <T> JsonElement toJsonObject(T value) {
        return GSON.toJsonTree(value);
    }


    public Gson getGson() {
        return GSON;
    }

    public Gson getGsonFancy() {
        return GSON_FANCY;
    }
}

package com.unrealdinnerbone.unreallib.json;

import com.google.gson.GsonBuilder;
import com.unrealdinnerbone.unreallib.json.api.IJsonParser;
import com.unrealdinnerbone.unreallib.json.gson.GsonParser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;

public class JsonUtil {

    public static final IJsonParser DEFAULT = new GsonParser(builder -> builder);
    public static GsonParser createParser(Function<GsonBuilder, GsonBuilder> builderFunction) {
        return new GsonParser(builderFunction);
    }
    public static <T> T parsePath(Path file, IJsonParser parser, Class<T> tClass) throws Exception {
        return parser.parse(tClass, Files.readString(file));
    }

}

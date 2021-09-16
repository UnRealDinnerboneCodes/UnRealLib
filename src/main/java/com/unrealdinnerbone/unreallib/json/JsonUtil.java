package com.unrealdinnerbone.unreallib.json;

import java.nio.file.Files;
import java.nio.file.Path;

public class JsonUtil {

    public static final IJsonParser<?> DEFAULT = MoshiParser.INSTANCE;

    public static <T> T parsePath(Path file, IJsonParser<?> parser, Class<T> tClass) throws Exception {
        return parser.parse(tClass, Files.readString(file));
    }

}

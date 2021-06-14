package com.unrealdinnerbone.unreallib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class JsonUtil {
    public static final Gson GSON = new GsonBuilder().disableHtmlEscaping().serializeNulls().setPrettyPrinting().create();

    public static <T> T parsePath(Path file, Gson gson, Class<T> tClass) throws IOException {
        return gson.fromJson(Files.readString(file), tClass);
    }

    public static <T> String listToJsonString(List<T> tList) {
        if (tList == null) {
            return "{}";
        }
        return JsonUtil.GSON.toJson(tList);
    }

}

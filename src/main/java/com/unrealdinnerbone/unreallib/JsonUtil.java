package com.unrealdinnerbone.unreallib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
    public static final Gson GSON = new GsonBuilder().disableHtmlEscaping().serializeNulls().setPrettyPrinting().create();

}

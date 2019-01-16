package com.unrealdinnerbone.unreallib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;

import java.lang.reflect.Modifier;

public class JsonHelper
{
    @Getter private static final Gson basicGson;
    @Getter private static final Gson configJson;

    static {
        basicGson = new GsonBuilder().create();
        configJson = new GsonBuilder().setPrettyPrinting().serializeNulls().excludeFieldsWithModifiers(Modifier.TRANSIENT).create();
    }

}

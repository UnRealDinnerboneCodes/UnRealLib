package com.unrealdinnerbone.unreallib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;

import java.lang.reflect.Modifier;

public class JsonUtil
{
    @Getter private static final Gson basicGson;

    static {
        basicGson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
    }



}

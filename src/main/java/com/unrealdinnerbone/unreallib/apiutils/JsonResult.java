package com.unrealdinnerbone.unreallib.apiutils;

import com.unrealdinnerbone.unreallib.json.IJsonParser;
import com.unrealdinnerbone.unreallib.json.JsonParseException;
import com.unrealdinnerbone.unreallib.json.JsonUtil;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonResult<T> implements IReturnResult<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonResult.class);

    private final String value;
    private final Class<T> tClass;

    private final IJsonParser jsonParser;
    private T t;


    public JsonResult(IJsonParser parser, String value, Class<T> tClass) {
        this.jsonParser = parser;
        this.value = value;
        this.tClass = tClass;
    }

    public JsonResult(String value, Class<T> tClass) {
        this(JsonUtil.DEFAULT, value, tClass);
    }

    public String getRawValue() {
        return value;
    }

    @Nullable
    public T get() {
        if (t == null) {
            try {
                return getExceptionally();
            } catch (JsonParseException e) {
                LOGGER.error("There was an error parsing the json", e);
                return null;
            }
        }else {
            return t;
        }
    }


    public T getExceptionally() throws JsonParseException {
        if (t == null) {
            t = jsonParser.parse(tClass, value);
        }
        return t;
    }


    public Class<T> getClazz() {
        return tClass;
    }



}

package com.unrealdinnerbone.unreallib.apiutils;

import com.unrealdinnerbone.unreallib.json.IJsonParser;
import com.unrealdinnerbone.unreallib.json.JsonParseException;
import com.unrealdinnerbone.unreallib.json.JsonUtil;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class ReturnResult<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReturnResult.class);

    private final String value;
    private final Class<T> tClass;

    private final IJsonParser jsonParser;
    private T t;

    public ReturnResult(IJsonParser parser, String value, Class<T> tClass) {
        this.jsonParser = parser;
        this.value = value;
        this.tClass = tClass;
    }

    public ReturnResult(String value, Class<T> tClass) {
        this(JsonUtil.DEFAULT, value, tClass);
    }

    public String getRawValue() {
        return value;
    }

    @Nullable
    public T get() {
        if (t == null) {
            try {
                t = getExceptionally();
            } catch (JsonParseException e) {
                LOGGER.error("There was an error parsing the json", e);
                return null;
            }
        }
        return t;
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


    public static <T> ReturnResult<T> createException(Throwable throwable) {
        return new ReturnResult<>(null, null) {
            @Override
            public T getExceptionally() {
                throw new RuntimeException(throwable);
            }
        };
    }
}

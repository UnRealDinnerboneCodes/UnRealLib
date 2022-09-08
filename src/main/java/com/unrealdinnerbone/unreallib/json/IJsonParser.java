package com.unrealdinnerbone.unreallib.json;

public interface IJsonParser {
    <T> T parse(Class<T> tClass, String value) throws JsonParseException;

    <T> String toJson(Class<T> tClass, T value);

    <T> String toFancyJson(Class<T> tClass, T value);

    <T> Object toJsonObject(Class<T> tClass, T value);
}

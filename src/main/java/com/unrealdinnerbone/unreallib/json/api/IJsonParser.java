package com.unrealdinnerbone.unreallib.json.api;

import java.util.List;

public interface IJsonParser {
    <T> T parse(Class<T> tClass, String value) throws JsonParseException;

    <T> List<T> parseList(Class<T[]> tClass, String value) throws JsonParseException;

    <T> String toJson(Class<T> tClass, T value);

    <T> String toFancyJson(Class<T> tClass, T value);

    <T> Object toJsonObject(Class<T> tClass, T value);
}

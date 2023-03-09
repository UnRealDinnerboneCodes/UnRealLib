package com.unrealdinnerbone.unreallib.json.api;

import com.unrealdinnerbone.unreallib.json.exception.JsonParseException;

import java.util.List;

public interface IJsonParser {
    <T> T parse(Class<T> tClass, String value) throws JsonParseException;

    <T> List<T> parseList(Class<T[]> tClass, String value) throws JsonParseException;

    <T> String toJson(T value);

    <T> String toFancyJson(T value);

    <T> Object toJsonObject(T value);
}

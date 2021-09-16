package com.unrealdinnerbone.unreallib.json;

public interface IJsonParser<E extends Exception> {
    <T> T parse(Class<T> tClass, String value) throws E;

    <T> String toJson(Class<T> tClass, T value);
}

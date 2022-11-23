package com.unrealdinnerbone.unreallib.json.api;

public interface IHasClazz<T extends IHasClazz<T>> {
    Class<T> getClazz();
}

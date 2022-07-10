package com.unrealdinnerbone.unreallib.json.moshi;

public interface IHasClazz<T extends IHasClazz<T>> {

    Class<T> getClazz();
}

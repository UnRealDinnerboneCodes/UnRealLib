package com.unrealdinnerbone.unreallib.json.gson.factory;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.unrealdinnerbone.unreallib.either.Either;
import com.unrealdinnerbone.unreallib.json.api.IID;
import com.unrealdinnerbone.unreallib.json.api.JsonRegistry;
import com.unrealdinnerbone.unreallib.json.api.JsonString;
import com.unrealdinnerbone.unreallib.json.gson.parsers.EitherAdapter;
import com.unrealdinnerbone.unreallib.json.gson.parsers.JsonStringAdapter;
import com.unrealdinnerbone.unreallib.json.gson.parsers.basic.IIDJsonAdapter;
import com.unrealdinnerbone.unreallib.json.gson.parsers.basic.RegistryJsonAdapter;

public class GsonWarpedFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<? super T> rawType = type.getRawType();
        if(rawType.equals(Either.class)) {
            return (TypeAdapter<T>) new EitherAdapter(gson);
        }
        if(rawType.equals(JsonString.class)) {
            return (TypeAdapter<T>) new JsonStringAdapter(gson);
        }
        if (IID.class.isAssignableFrom(rawType) && rawType.isEnum()) {
            return (TypeAdapter<T>) new IIDJsonAdapter<>(rawType.getEnumConstants());
        }
        JsonRegistry<?> forType = JsonRegistry.findForType(rawType);
        if(forType != null) {
            return RegistryJsonAdapter.create(forType);
        }
        return null;
    }
}

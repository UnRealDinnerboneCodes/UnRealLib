package com.unrealdinnerbone.unreallib.json.moshi;

import com.squareup.moshi.*;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;
import java.util.Set;

public final class OptionalFactory implements JsonAdapter.Factory {
    @Nullable
    @Override
    public JsonAdapter<?> create(Type type, Set<? extends Annotation> annotations, Moshi moshi) {
        if (!annotations.isEmpty()) return null;
        if (!(type instanceof ParameterizedType)) return null;

        Class<?> rawType = Types.getRawType(type);
        if (rawType != Optional.class) return null;

        Type optionalType = ((ParameterizedType) type).getActualTypeArguments()[0];

        JsonAdapter<?> optionalTypeAdapter = moshi.adapter(optionalType).nullSafe();

        return new OptionalJsonAdapter<>(optionalTypeAdapter);
    }

    private static class OptionalJsonAdapter<T> extends JsonAdapter<Optional<T>> {

        private final JsonAdapter<T> optionalTypeAdapter;

        public OptionalJsonAdapter(JsonAdapter<T> optionalTypeAdapter) {
            this.optionalTypeAdapter = optionalTypeAdapter;
        }

        @Nullable
        @Override
        public Optional<T> fromJson(JsonReader reader) throws IOException {
            T instance = optionalTypeAdapter.fromJson(reader);
            return Optional.ofNullable(instance);
        }

        @Override
        public void toJson(JsonWriter writer, @Nullable Optional<T> value) throws IOException {
            if (value.isPresent()) {
                optionalTypeAdapter.toJson(writer, value.get());
            } else {
                writer.nullValue();
            }
        }
    }
}

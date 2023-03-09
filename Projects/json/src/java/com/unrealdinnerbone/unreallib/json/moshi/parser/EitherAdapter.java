package com.unrealdinnerbone.unreallib.json.moshi.parser;

import com.squareup.moshi.*;
import com.unrealdinnerbone.unreallib.either.Either;
import com.unrealdinnerbone.unreallib.json.api.IJsonParser;

import java.io.IOException;

public abstract class EitherAdapter extends JsonAdapter<Either<?, ?>> {
    @Override
    @FromJson
    public Either<?, ?> fromJson(JsonReader reader) throws IOException {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    @ToJson
    public void toJson(JsonWriter writer, Either<?, ?> value) throws IOException {
        writer.jsonValue(getJsonParser().toJsonObject(GenericClass.of(value.getValue()), value.getValue()));
    }

    public abstract IJsonParser getJsonParser();


    public static class GenericClass<T> {

        private final T type;

        public GenericClass(T type) {
            this.type = type;
        }

        public Class<T> getMyType() {
            return (Class<T>) this.type.getClass();
        }

        public static <T> Class<T> of(T type) {
            return new GenericClass<>(type).getMyType();
        }
    }
}

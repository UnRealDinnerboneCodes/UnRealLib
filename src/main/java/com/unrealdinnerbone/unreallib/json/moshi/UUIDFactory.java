package com.unrealdinnerbone.unreallib.json.moshi;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.UUID;

public class UUIDFactory implements JsonAdapter.Factory {

    @Override
    public JsonAdapter<UUID> create(Type type, Set<? extends Annotation> annotations, Moshi moshi) {
        return new JsonAdapter<>() {
            @Override
            public UUID fromJson(JsonReader reader) throws IOException {
                return UUID.fromString(reader.nextString());
            }

            @Override
            public void toJson(JsonWriter writer, UUID value) throws IOException {
                writer.value(value.toString());
            }
        };
    }
}

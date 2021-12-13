package com.unrealdinnerbone.unreallib.json.moshi;

import com.squareup.moshi.*;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.UUID;

public class UUIDFactory extends JsonAdapter<UUID> {

    @Override
    @FromJson
    public UUID fromJson(JsonReader reader) throws IOException {
        return UUID.fromString(reader.nextString());
    }

    @Override
    @ToJson
    public void toJson(JsonWriter writer, UUID value) throws IOException {
        if(value == null) {
            writer.value(value.toString());
        }else {
            writer.nullValue();
        }
    }
}

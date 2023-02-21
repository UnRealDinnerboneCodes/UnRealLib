package com.unrealdinnerbone.unreallib.json.moshi.parser;

import com.squareup.moshi.*;

import java.io.IOException;
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
        writer.value(value != null ? value.toString() : null);
    }
}

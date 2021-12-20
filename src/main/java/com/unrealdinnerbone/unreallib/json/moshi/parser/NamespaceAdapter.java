package com.unrealdinnerbone.unreallib.json.moshi.parser;

import com.squareup.moshi.*;
import com.unrealdinnerbone.unreallib.Namespace;

import java.io.IOException;

public class NamespaceAdapter extends JsonAdapter<Namespace> {

    @Override
    @FromJson
    public Namespace fromJson(JsonReader reader) throws IOException {
        String value = reader.nextString();
        String[] keyValue = value.split(":");
        if(keyValue.length == 2) {
            return new Namespace(keyValue[0], keyValue[1]);
        }else {
            throw new IOException("Invalid namespace: " + value);
        }
    }

    @Override
    @ToJson
    public void toJson(JsonWriter writer, Namespace value) throws IOException {
            if(value != null) {
                writer.value(value.toString());
            }
    }
}

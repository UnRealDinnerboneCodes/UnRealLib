package com.unrealdinnerbone.unreallib.json.moshi.parser;

import com.squareup.moshi.*;
import com.unrealdinnerbone.unreallib.json.api.DataString;
import okio.Buffer;

import java.io.IOException;

public class RawJsonAdapter
{
    @ToJson
    public void toJson(JsonWriter writer, @DataString String string) throws IOException {
        try(Buffer buffer = new Buffer()) {
            writer.value(buffer.writeUtf8(string).readUtf8());
        }
    }

    @FromJson
    @DataString
    public String fromJson(JsonReader reader, JsonAdapter<Object> delegate) throws IOException {
        return delegate.toJson(reader.readJsonValue());
    }

}

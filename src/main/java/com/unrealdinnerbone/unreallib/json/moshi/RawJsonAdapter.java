package com.unrealdinnerbone.unreallib.json.moshi;

import com.squareup.moshi.*;
import okio.Buffer;

import java.io.IOException;

public class RawJsonAdapter
{
    @ToJson
    public void toJson(JsonWriter writer, @DataString String string) throws IOException {
        writer.value(new Buffer().writeUtf8(string).readUtf8());
    }

    @FromJson
    @DataString
    public String fromJson(JsonReader reader, JsonAdapter<Object> delegate) throws IOException {
        return delegate.toJson(reader.readJsonValue());
    }

}

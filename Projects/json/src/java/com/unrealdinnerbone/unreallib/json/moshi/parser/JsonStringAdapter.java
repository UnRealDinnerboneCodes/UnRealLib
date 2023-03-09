package com.unrealdinnerbone.unreallib.json.moshi.parser;

import com.squareup.moshi.*;
import com.unrealdinnerbone.unreallib.json.api.JsonString;
import com.unrealdinnerbone.unreallib.json.JsonUtil;
import okio.Buffer;

import java.io.IOException;

public class JsonStringAdapter extends JsonAdapter<JsonString> {
    @Override
    public JsonString fromJson(JsonReader reader) throws IOException {
        return new JsonString(JsonUtil.DEFAULT.toJson(Object.class, reader.readJsonValue()));
    }

    @Override
    public void toJson(JsonWriter writer, JsonString string) throws IOException {
        writer.value(new Buffer().writeUtf8(string.string()).readUtf8());
    }
}

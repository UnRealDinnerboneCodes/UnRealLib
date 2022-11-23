package com.unrealdinnerbone.unreallib.json.moshi.parser;

import com.squareup.moshi.*;
import com.unrealdinnerbone.unreallib.JustAString;
import com.unrealdinnerbone.unreallib.json.JsonUtil;
import okio.Buffer;

import java.io.IOException;

public class JustAStringAdapter extends JsonAdapter<JustAString> {


//    @FromJson
//    @DataString
//    public JustAString fromJson(JsonReader reader, JsonAdapter<Object> delegate) throws IOException {
//        return new JustAString(delegate.toJson(reader.readJsonValue()));
//    }

    @Override
    public JustAString fromJson(JsonReader reader) throws IOException {
        return new JustAString(JsonUtil.DEFAULT.toJson(Object.class, reader.readJsonValue()));
    }

    @Override
    public void toJson(JsonWriter writer, JustAString string) throws IOException {
        writer.value(new Buffer().writeUtf8(string.string()).readUtf8());
    }
}

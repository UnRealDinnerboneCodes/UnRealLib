package com.unrealdinnerbone.unreallib.json.gson.parsers.basic;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.unrealdinnerbone.unreallib.SimpleColor;

import java.awt.*;
import java.io.IOException;

public class SimpleColorAdapter extends TypeAdapter<SimpleColor> {
    @Override
    public void write(JsonWriter out, SimpleColor value) throws IOException {
        if (value != null) {
            out.value(value.hex());
        } else {
            out.nullValue();
        }
    }

    @Override
    public SimpleColor read(JsonReader in) throws IOException {
        String rgb = in.nextString();
        try {
            return SimpleColor.fromHex(rgb);
        }catch (IllegalArgumentException e) {
            throw new IOException(e);
        }
    }
}

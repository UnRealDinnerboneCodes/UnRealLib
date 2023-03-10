package com.unrealdinnerbone.unreallib.json.gson.parsers.basic;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.awt.*;
import java.io.IOException;

public class ColorAdapter extends TypeAdapter<Color> {
    @Override
    public void write(JsonWriter out, Color value) throws IOException {
        out.value(value.getRGB());
    }

    @Override
    public Color read(JsonReader in) throws IOException {
        return new Color(in.nextInt());
    }
}

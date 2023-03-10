package com.unrealdinnerbone.unreallib.json.gson.parsers.basic;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.awt.*;
import java.io.IOException;

public class ColorAdapter extends TypeAdapter<Color> {
    @Override
    public void write(JsonWriter out, Color value) throws IOException {
        if (value != null) {
            int rgb = value.getRed();
            rgb = (rgb << 8) + value.getGreen();
            rgb = (rgb << 8) + value.getBlue();
            out.value(rgb);
        } else {
            out.nullValue();
        }
    }

    @Override
    public Color read(JsonReader in) throws IOException {
        int rgb = in.nextInt();
        int blue = rgb & 0xFF;
        rgb >>= 8;
        int green = rgb & 0xFF;
        rgb >>= 8;
        int red = rgb & 0xFF;
        return new Color(red, green, blue);
    }
}

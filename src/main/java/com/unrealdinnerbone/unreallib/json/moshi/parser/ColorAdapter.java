package com.unrealdinnerbone.unreallib.json.moshi.parser;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;

import java.awt.*;
import java.io.IOException;

public class ColorAdapter extends JsonAdapter<Color> {

    @Override
    @FromJson
    public Color fromJson(JsonReader reader) throws IOException {
        int rgb = reader.nextInt();
        int blue = rgb & 0xFF;
        rgb >>= 8;
        int green = rgb & 0xFF;
        rgb >>= 8;
        int red = rgb & 0xFF;
        return new Color(red, green, blue);
    }

    @Override
    public void toJson(JsonWriter writer, Color value) throws IOException {
        if(value == null) {
            writer.nullValue();
        }else {
            int rgb = value.getRed();
            rgb = (rgb << 8) + value.getGreen();
            rgb = (rgb << 8) + value.getBlue();
            writer.value(rgb);
        }

    }

}
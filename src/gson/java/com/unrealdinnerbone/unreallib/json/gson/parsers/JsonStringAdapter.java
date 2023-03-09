package com.unrealdinnerbone.unreallib.json.gson.parsers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.unrealdinnerbone.unreallib.json.api.JsonString;

import java.io.IOException;

public class JsonStringAdapter extends TypeAdapter<JsonString> {

    private final Gson gson;

    public JsonStringAdapter(Gson gson) {
        this.gson = gson;
    }
    @Override
    public void write(JsonWriter out, JsonString value) throws IOException {
        out.value(value.json());
    }

    @Override
    public JsonString read(JsonReader in) throws IOException {
        JsonElement json = gson.fromJson(in, JsonElement.class);
        return new JsonString(gson.toJson(json));
    }
}

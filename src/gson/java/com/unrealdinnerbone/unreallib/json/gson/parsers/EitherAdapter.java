package com.unrealdinnerbone.unreallib.json.gson.parsers;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.unrealdinnerbone.unreallib.either.Either;

import java.io.IOException;

public class EitherAdapter extends TypeAdapter<Either<?, ?>> {

    private final Gson gson;

    public EitherAdapter(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void write(JsonWriter out, Either<?, ?> value) throws IOException {
        out.jsonValue(gson.toJson(value.getValue()));
    }

    @Override
    public Either<?, ?> read(JsonReader in) throws IOException {
        return null;
    }

}

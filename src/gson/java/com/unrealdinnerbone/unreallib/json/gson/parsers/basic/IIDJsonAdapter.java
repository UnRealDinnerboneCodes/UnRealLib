package com.unrealdinnerbone.unreallib.json.gson.parsers.basic;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.unrealdinnerbone.unreallib.json.api.IID;


import java.io.IOException;


public class IIDJsonAdapter<T extends Enum<T> & IID> extends TypeAdapter<T> {
    private final T[] constants;

    public IIDJsonAdapter(Object[] enumConstants) {
        this.constants = (T[]) enumConstants;
    }

    @Override
    public void write(JsonWriter out, T value) throws IOException {
        if (value != null) {
            out.value(value.getId());
        } else {
            out.nullValue();
        }
    }

    @Override
    public T read(JsonReader in) throws IOException {
        int id = in.nextInt();
        for (T constant : constants) {
            if(id == constant.getId()) {
                return constant;
            }
        }
        throw new IOException("Could not find " + id + " in " + constants.getClass().getSimpleName());
    }
}
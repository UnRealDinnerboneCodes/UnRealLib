package com.unrealdinnerbone.unreallib.json.gson.parsers.basic;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.unrealdinnerbone.unreallib.LogHelper;
import com.unrealdinnerbone.unreallib.Namespace;
import com.unrealdinnerbone.unreallib.StringUtils;
import org.slf4j.Logger;

import java.io.IOException;

public class NamespaceAdapter extends TypeAdapter<Namespace> {
    private static final Logger LOGGER = LogHelper.getLogger();

    @Override
    public void write(JsonWriter out, Namespace value) throws IOException {
        if(value != null) {
            out.value(value.toString());
        }else {
            out.nullValue();
        }
    }

    @Override
    public Namespace read(JsonReader in) throws IOException {
        String value = in.nextString();
        String[] keyValue = value.split(":");
        if(keyValue.length == 2) {
            if(StringUtils.containsUpperCase(value)) {
                LOGGER.warn("Namespace {} contains upper case letters", value);
            }
            return new Namespace(keyValue[0], keyValue[1]);
        }else {
            throw new IOException("Invalid namespace: " + value);
        }
    }
}

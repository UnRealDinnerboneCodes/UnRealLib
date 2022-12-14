package com.unrealdinnerbone.unreallib.json.moshi.parser;

import com.squareup.moshi.*;
import com.unrealdinnerbone.unreallib.LogHelper;
import com.unrealdinnerbone.unreallib.Namespace;
import com.unrealdinnerbone.unreallib.StringUtils;
import org.slf4j.Logger;

import java.io.IOException;

public class NamespaceAdapter extends JsonAdapter<Namespace> {

    private static final Logger LOGGER = LogHelper.getLogger();
    @Override
    @FromJson
    public Namespace fromJson(JsonReader reader) throws IOException {
        String value = reader.nextString();
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

    @Override
    @ToJson
    public void toJson(JsonWriter writer, Namespace value) throws IOException {
        if (value != null) {
            writer.value(value.toString());
        }
    }
}

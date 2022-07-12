package com.unrealdinnerbone.unreallib.json.moshi.parser;

import com.squareup.moshi.*;
import com.unrealdinnerbone.unreallib.Pair;
import com.unrealdinnerbone.unreallib.json.MoshiParser;

import java.io.IOException;
import java.util.Map;

public abstract class PairParser<A, B> extends JsonAdapter<Pair<A, B>> {

    @Override
    @FromJson
    public Pair<A, B> fromJson(JsonReader reader) throws IOException {
        try {
            Map<A, B> value = getJsonParser().parse(Map.class, reader);
            if(value.size() != 1) {
                throw new IOException("Expected a pair, but got " + value.size() + " entries");
            }else {
                for(Map.Entry<A, B> entry : value.entrySet()) {
                    return Pair.of(entry.getKey(), entry.getValue());
                }
            }
        }catch(Exception e) {
            throw new IOException(e);
        }
        throw new UnsupportedOperationException();
    }

    @Override
    @ToJson
    public void toJson(JsonWriter writer, Pair<A, B> value) throws IOException {
        writer.jsonValue(getJsonParser().toJsonObject(Map.class, Map.of(String.valueOf(value.key()), String.valueOf(value.value()))));
    }

    public abstract MoshiParser getJsonParser();
}

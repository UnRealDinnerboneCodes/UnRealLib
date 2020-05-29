package com.unrealdinnerbone.unreallib;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.function.Consumer;

public class GsonStorageEntry<T> {
    private final static JsonParser PARSER = new JsonParser();

    private final String value;
    private CacheStore<T> t;
    private CacheStore<String> reformetedString;
    private CacheStore<String> rawReformetedString;

    public GsonStorageEntry(String value, Class<T> tClass) {
        this.value = value;

        this.t = new CacheStore<>(() -> JsonUtil.getBasicGson().fromJson(value, tClass));

        this.reformetedString = new CacheStore<>(() -> JsonUtil.getBasicGson().toJson(get()));

        this.rawReformetedString = new CacheStore<>(() -> {
            JsonElement jsonElement = PARSER.parse(value);
            JsonElement theRealJson = null;
            if (jsonElement.isJsonArray()) {
                theRealJson = jsonElement.getAsJsonArray();
            } else if (jsonElement.isJsonObject()) {
                theRealJson = jsonElement.getAsJsonObject();
            }
            return JsonUtil.getBasicGson().toJson(theRealJson);
        });
    }

    public String getRawValue() {
        return value;
    }

    public T get() {
        return t.getT();
    }

    public void ifNotNull(Consumer<T> tConsumer) {
        if(get() != null) {
            tConsumer.accept(get());
        }
    }

    public String getReformtedJson() {
        return reformetedString.getT();
    }

    public String getRawFormattedJson() {
       return rawReformetedString.getT();
    }
}

package com.unrealdinnerbone.unreallib.json.moshi.parser;

import com.squareup.moshi.*;
import com.unrealdinnerbone.unreallib.json.api.IID;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;


public class EnumJsonAdapter<T extends Enum<T> & IID> extends JsonAdapter<T> {
    private final T[] constants;

    public static <T extends Enum<T> & IID> EnumJsonAdapter<T> create(Class<T> enumType) {
        return new EnumJsonAdapter<>(enumType);
    }

    public EnumJsonAdapter(Class<T> enumType) {
        constants = enumType.getEnumConstants();
    }

    @Override
    @FromJson
    public @NotNull T fromJson(JsonReader reader) throws IOException {
        int id = reader.nextInt();
        for (T constant : constants) {
            if(id == constant.getId()) {
                return constant;
            }
        }
        throw new IOException("Invalid number for enum");
    }

    @Override
    @ToJson
    public void toJson(JsonWriter writer, T value) throws IOException {
        writer.value(value.getId());
    }

}
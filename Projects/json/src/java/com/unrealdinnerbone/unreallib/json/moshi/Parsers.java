package com.unrealdinnerbone.unreallib.json.moshi;

import com.squareup.moshi.JsonAdapter;
import com.unrealdinnerbone.unreallib.json.api.IID;
import com.unrealdinnerbone.unreallib.json.moshi.parser.IIDJsonAdapter;

public class Parsers
{
    public static <T extends Enum<T> & IID> JsonAdapter<T> create(Class<T> enumType) {
        return IIDJsonAdapter.create(enumType);
    }
}

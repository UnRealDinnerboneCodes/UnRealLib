package com.unrealdinnerbone.unreallib.apiutils;

import com.unrealdinnerbone.unreallib.json.JsonParseException;
import org.jetbrains.annotations.Nullable;

public interface IReturnResult<T> {


    @Nullable
    T get();

    T getExceptionally() throws JsonParseException;



    static <T> IReturnResult<T> createException(JsonParseException throwable) {
        return new IReturnResult<>() {
            @Override
            public @Nullable T get() {
                return null;
            }

            @Override
            public T getExceptionally() throws JsonParseException {
                throw throwable;
            }
        };
    }

}

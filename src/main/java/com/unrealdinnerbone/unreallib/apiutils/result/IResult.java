package com.unrealdinnerbone.unreallib.apiutils.result;

import com.unrealdinnerbone.unreallib.exception.ExceptionFunction;
import com.unrealdinnerbone.unreallib.exception.WebResultException;
import com.unrealdinnerbone.unreallib.json.exception.JsonParseException;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public interface IResult<T> {

    T getNow() throws WebResultException, JsonParseException;

    CompletableFuture<T> get();

    default <R> IResult<R> map(ExceptionFunction<JsonParseException, T, R> function) {

        return new IResult<>() {
            @Override
            public R getNow() throws WebResultException, JsonParseException {
                return function.apply(IResult.this.getNow());
            }

            @Override
            public CompletableFuture<R> get() {
                return IResult.this.get().thenApply(function::apply);
            }
        };
    }

    default <B, R extends IResult<B>> R mapSelf(Function<IResult<T>, R> map) {
        return map.apply(this);
    }

}

package com.unrealdinnerbone.unreallib.apiutils;

import com.unrealdinnerbone.unreallib.json.api.IJsonParser;
import com.unrealdinnerbone.unreallib.json.api.JsonParseException;
import com.unrealdinnerbone.unreallib.json.JsonUtil;
import com.unrealdinnerbone.unreallib.web.HttpHelper;
import org.jetbrains.annotations.NotNull;

import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class APIUtils {

    private static <T> CompletableFuture<T> mapJson(CompletableFuture<HttpResponse<String>> responseCompletableFuture, Class<T> tClass) {
        return responseCompletableFuture.thenApply(response -> {
            if (response.statusCode() == 200) {
                return JsonUtil.DEFAULT.parse(tClass, response.body());
            } else {
                throw new WebResultException(response.uri().toString(), response.body(), response.statusCode());
            }
        });
    }

    public static <T> IResult<T> getResult(HttpHelper httpHelper, Class<T> tClass, String urlData, IJsonParser parser) {
        return new IResult<>() {
            @Override
            public T getNow() throws WebResultException, JsonParseException {
                return parser.parse(tClass, httpHelper.getOrThrow(urlData));
            }

            @Override
            public CompletableFuture<T> get() {
                return mapJson(httpHelper.getAsync(urlData), tClass);
            }
        };
    }
    @NotNull
    public static <T> IResult<T> get(Class<T> tClass, String urlData) {
       return getResult(HttpHelper.DEFAULT, tClass, urlData, JsonUtil.DEFAULT);
    }

}

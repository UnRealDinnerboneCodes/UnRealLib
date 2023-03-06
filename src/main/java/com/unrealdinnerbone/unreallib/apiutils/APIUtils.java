package com.unrealdinnerbone.unreallib.apiutils;

import com.unrealdinnerbone.unreallib.exception.WebResultException;
import com.unrealdinnerbone.unreallib.json.JsonUtil;
import com.unrealdinnerbone.unreallib.json.api.IJsonParser;
import com.unrealdinnerbone.unreallib.web.HttpHelper;
import com.unrealdinnerbone.unreallib.web.IContentType;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

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

    public static <T> IResult<T> getResult(HttpClient client, Class<T> tClass, String urlData, IJsonParser parser, Function<HttpRequest.Builder, HttpRequest.Builder> builder) {
        return new IResult<>() {
            @Override
            public T getNow() throws WebResultException {
                return parser.parse(tClass, HttpHelper.getOrThrow(client, URI.create(urlData), builder));
            }

            @Override
            public CompletableFuture<T> get() {
                return mapJson(HttpHelper.getAsync(client, URI.create(urlData), builder), tClass);
            }
        };
    }

    public static <T> IResult<T> postResult(HttpClient client, Class<T> tClass, String urlData, IJsonParser parser, String map, IContentType type, Function<HttpRequest.Builder, HttpRequest.Builder> builder) {
        return new IResult<>() {
            @Override
            public T getNow() throws WebResultException {
                return parser.parse(tClass, HttpHelper.postOrThrow(client, URI.create(urlData), map, type, builder));
            }

            @Override
            public CompletableFuture<T> get() {
                return mapJson(HttpHelper.postAsync(client, URI.create(urlData), map, type, builder), tClass);
            }
        };
    }

    public static <T> IResult<T> getResult(HttpClient client, Class<T> tClass, String urlData, IJsonParser parser) {
        return getResult(client, tClass, urlData, parser, builder -> builder);
    }
    @NotNull
    public static <T> IResult<T> get(HttpClient client, Class<T> tClass, String urlData, Function<HttpRequest.Builder, HttpRequest.Builder> builder) {
        return getResult(client, tClass, urlData, JsonUtil.DEFAULT, builder);
    }

    @NotNull
    public static <T> IResult<T> get(HttpClient client, Class<T> tClass, String urlData) {
        return getResult(client, tClass, urlData, JsonUtil.DEFAULT, builder -> builder);
    }

    @NotNull
    public static <T> IResult<T> get(Class<T> tClass, String urlData, Function<HttpRequest.Builder, HttpRequest.Builder> builder) {
        return getResult(HttpHelper.DEFAULT, tClass, urlData, JsonUtil.DEFAULT, builder);
    }

    @NotNull
    public static <T> IResult<T> get(Class<T> tClass, String urlData) {
        return getResult(HttpHelper.DEFAULT, tClass, urlData, JsonUtil.DEFAULT, builder -> builder);
    }

}

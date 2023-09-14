package com.unrealdinnerbone.unreallib.apiutils;

import com.unrealdinnerbone.unreallib.exception.WebResultException;
import com.unrealdinnerbone.unreallib.json.JsonUtil;
import com.unrealdinnerbone.unreallib.json.api.IJsonParser;
import com.unrealdinnerbone.unreallib.web.HttpHelper;
import com.unrealdinnerbone.unreallib.web.IContentType;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class APIUtils {

    private static <T> CompletableFuture<ResponseData<T>> mapJson(CompletableFuture<HttpResponse<String>> responseCompletableFuture, Class<T> tClass) {
        return responseCompletableFuture.thenApply(response -> {
            if (response.statusCode() == 200) {
                return new ResponseData<>(JsonUtil.DEFAULT.parse(tClass, response.body()), response.statusCode(), response.headers().map());
            } else {
                throw new WebResultException(response.uri().toString(), response.body(), response.statusCode());
            }
        });
    }

    public static <T> IResult<ResponseData<T>> getResult(HttpClient client, Class<T> tClass, String urlData, IJsonParser parser, Function<HttpRequest.Builder, HttpRequest.Builder> builder) {
        return new IResult<>() {
            @Override
            public ResponseData<T> getNow() throws WebResultException {
                try {
                    HttpResponse<String> stringHttpResponse = HttpHelper.get(client, URI.create(urlData), builder);
                    LoggerFactory.getLogger(APIUtils.class).info("Response: " + stringHttpResponse.body());
                    return new ResponseData<>(parser.parse(tClass, stringHttpResponse.body()), stringHttpResponse.statusCode(), stringHttpResponse.headers().map());
                } catch (IOException | InterruptedException e) {
                    throw new WebResultException(e);
                }
            }

            @Override
            public CompletableFuture<ResponseData<T>> get() {
                return mapJson(HttpHelper.getAsync(client, URI.create(urlData), builder), tClass);
            }
        };
    }

    public static <T> IResult<ResponseData<T>> postResult(HttpClient client, Class<T> tClass, String urlData, IJsonParser parser, String map, IContentType type, Function<HttpRequest.Builder, HttpRequest.Builder> builder) {
        return new IResult<>() {
            @Override
            public ResponseData<T> getNow() throws WebResultException {
                try {
                    HttpResponse<String> post = HttpHelper.post(client, URI.create(urlData), map, type, builder);
                    return new ResponseData<>(parser.parse(tClass, post.body()), post.statusCode(), post.headers().map());
                } catch (IOException | InterruptedException e) {
                    throw new WebResultException(e);
                }
            }

            @Override
            public CompletableFuture<ResponseData<T>> get() {
                return mapJson(HttpHelper.postAsync(client, URI.create(urlData), map, type, builder), tClass);
            }
        };
    }

    public static <T> IResult<ResponseData<T>> getResult(HttpClient client, Class<T> tClass, String urlData, IJsonParser parser) {
        return getResult(client, tClass, urlData, parser, builder -> builder);
    }
    @NotNull
    public static <T> IResult<ResponseData<T>> get(HttpClient client, Class<T> tClass, String urlData, Function<HttpRequest.Builder, HttpRequest.Builder> builder) {
        return getResult(client, tClass, urlData, JsonUtil.DEFAULT, builder);
    }

    @NotNull
    public static <T> IResult<ResponseData<T>> get(HttpClient client, Class<T> tClass, String urlData) {
        return getResult(client, tClass, urlData, JsonUtil.DEFAULT, builder -> builder);
    }

    @NotNull
    public static <T> IResult<ResponseData<T>> get(Class<T> tClass, String urlData, Function<HttpRequest.Builder, HttpRequest.Builder> builder) {
        return getResult(HttpHelper.DEFAULT, tClass, urlData, JsonUtil.DEFAULT, builder);
    }

    @NotNull
    public static <T> IResult<ResponseData<T>> get(Class<T> tClass, String urlData) {
        return getResult(HttpHelper.DEFAULT, tClass, urlData, JsonUtil.DEFAULT, builder -> builder);
    }

}

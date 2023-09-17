package com.unrealdinnerbone.unreallib.apiutils;

import com.unrealdinnerbone.unreallib.apiutils.result.IResult;
import com.unrealdinnerbone.unreallib.apiutils.result.JsonResult;
import com.unrealdinnerbone.unreallib.exception.WebResultException;
import com.unrealdinnerbone.unreallib.json.JsonUtil;
import com.unrealdinnerbone.unreallib.json.api.IJsonParser;
import com.unrealdinnerbone.unreallib.web.HttpHelper;
import com.unrealdinnerbone.unreallib.web.IContentType;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class APIUtils {

    public static IResult<HttpResponse<String>> getResult(HttpClient client, String urlData, Function<HttpRequest.Builder, HttpRequest.Builder> builder) {
        return new IResult<>() {
            @Override
            public HttpResponse<String> getNow() throws WebResultException {
                try {
                    return HttpHelper.get(client, URI.create(urlData), builder);
                } catch (IOException | InterruptedException e) {
                    throw new WebResultException(e);
                }
            }

            @Override
            public CompletableFuture<HttpResponse<String>> get() {
                return HttpHelper.getAsync(client, URI.create(urlData), builder);
            }
        };
    }

    public static IResult<HttpResponse<String>> postResult(HttpClient client, String urlData, String map, IContentType type, Function<HttpRequest.Builder, HttpRequest.Builder> builder) {
        return new IResult<>() {
            @Override
            public HttpResponse<String> getNow() throws WebResultException {
                try {
                    return HttpHelper.post(client, URI.create(urlData), map, type, builder);
                } catch (IOException | InterruptedException e) {
                    throw new WebResultException(e);
                }
            }

            @Override
            public CompletableFuture<HttpResponse<String>> get() {
                return HttpHelper.postAsync(client, URI.create(urlData), map, type, builder);
            }
        };
    }

    public static <T> JsonResult<T> getJson(HttpClient client, Class<T> tClass, String urlData, IJsonParser parser) {
        return getResult(client, urlData, builder -> builder).mapSelf(result -> new JsonResult<>(result, tClass, parser));
    }
    @NotNull
    public static <T> JsonResult<T> getJson(HttpClient client, Class<T> tClass, String urlData, Function<HttpRequest.Builder, HttpRequest.Builder> builder) {
        return getResult(client, urlData, builder).mapSelf(result -> new JsonResult<>(result, tClass, JsonUtil.DEFAULT));
    }

    @NotNull
    public static <T> JsonResult<T> getJson(HttpClient client, Class<T> tClass, String urlData) {
        return getResult(client,  urlData, builder -> builder).mapSelf(result -> new JsonResult<>(result, tClass, JsonUtil.DEFAULT));
    }

    @NotNull
    public static <T> JsonResult<T> getJson(Class<T> tClass, String urlData, Function<HttpRequest.Builder, HttpRequest.Builder> builder) {
        return getResult(HttpHelper.DEFAULT, urlData, builder).mapSelf(result -> new JsonResult<>(result, tClass, JsonUtil.DEFAULT));
    }

    @NotNull
    public static <T> JsonResult<T> getJson(Class<T> tClass, String urlData) {
        return getResult(HttpHelper.DEFAULT, urlData, builder -> builder).mapSelf(result -> new JsonResult<>(result, tClass, JsonUtil.DEFAULT));
    }

}

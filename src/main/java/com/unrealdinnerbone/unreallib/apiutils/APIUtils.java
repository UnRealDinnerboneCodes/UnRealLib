package com.unrealdinnerbone.unreallib.apiutils;

import com.unrealdinnerbone.unreallib.json.api.JsonParseException;
import com.unrealdinnerbone.unreallib.json.JsonUtil;
import com.unrealdinnerbone.unreallib.web.HttpHelper;
import org.jetbrains.annotations.NotNull;

import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class APIUtils {
    @NotNull
    public static <T> CompletableFuture<T> get(HttpHelper httpHelper, Class<T> tClass, String urlData) {
        return mapJson(httpHelper.getAsync(urlData), tClass);
    }

    public static <T> CompletableFuture<T> mapJson(CompletableFuture<HttpResponse<String>> responseCompletableFuture, Class<T> tClass) {
        CompletableFuture<T> completableFuture = new CompletableFuture<>();
        responseCompletableFuture.whenComplete((response, throwable) -> {
                    if (throwable != null) {
                        completableFuture.completeExceptionally(throwable);
                    } else {
                        if (response.statusCode() == 200) {
                            try {
                                completableFuture.complete(JsonUtil.DEFAULT.parse(tClass, response.body()));
                            } catch (JsonParseException e) {
                                completableFuture.completeExceptionally(e);
                            }
                        } else {
                            completableFuture.completeExceptionally(new WebResultException(response.uri().toString(), response.body(), response.statusCode()));
                        }
                    }
                });
        return completableFuture;
    }
    @NotNull
    public static <T> CompletableFuture<T> get(Class<T> tClass, String urlData) {
       return get(HttpHelper.DEFAULT, tClass, urlData);
    }

}

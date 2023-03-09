package com.unrealdinnerbone.unreallib.web;

import com.unrealdinnerbone.unreallib.exception.WebResultException;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class HttpHelper {

    public static final HttpClient DEFAULT = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    public static HttpResponse<String> post(HttpClient client, URI url, String map, IContentType contentType, Function<HttpRequest.Builder, HttpRequest.Builder> builder) throws IOException, InterruptedException {
        HttpRequest.Builder requestBuilder = builder.apply(HttpRequest.newBuilder()
                .uri(url)
                .POST(HttpRequest.BodyPublishers.ofString(map))
                .setHeader("Content-Type", contentType.getType()));
        return client.send(requestBuilder.build(), HttpResponse.BodyHandlers.ofString());
    }
    public static HttpResponse<String> post(HttpClient client, URI url, String data, IContentType contentType) throws IOException, InterruptedException {
        return post(client, url, data, contentType, builder -> builder);
    }

    public static HttpResponse<String> post(URI url, String data, IContentType contentType) throws IOException, InterruptedException {
        return post(DEFAULT, url, data, contentType, builder -> builder);
    }

    public static HttpResponse<String> post(URI url, String data, IContentType contentType, Function<HttpRequest.Builder, HttpRequest.Builder> builder) throws IOException, InterruptedException {
        return post(DEFAULT, url, data, contentType, builder);
    }


    public static CompletableFuture<HttpResponse<String>> postAsync(HttpClient client, URI url, String data, IContentType contentType, Function<HttpRequest.Builder, HttpRequest.Builder> builder) {
        HttpRequest.Builder requestBuilder = builder.apply(HttpRequest.newBuilder()
                .uri(url)
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .setHeader("Content-Type", contentType.getType()));
        return client.sendAsync(requestBuilder.build(), HttpResponse.BodyHandlers.ofString());
    }

    public static CompletableFuture<HttpResponse<String>> postAsync(HttpClient client, URI url, String map, IContentType contentType) {
        return postAsync(client, url, map, contentType, builder -> builder);
    }

    public static CompletableFuture<HttpResponse<String>> postAsync(URI url, String map, IContentType contentType) {
        return postAsync(DEFAULT, url, map, contentType, builder -> builder);
    }

    public static CompletableFuture<HttpResponse<String>> postAsync(URI url, String map, IContentType contentType, Function<HttpRequest.Builder, HttpRequest.Builder> builder) {
        return postAsync(DEFAULT, url, map, contentType, builder);
    }

    public static HttpResponse<String> get(HttpClient client, URI uri, Function<HttpRequest.Builder, HttpRequest.Builder> builder) throws IOException, InterruptedException {
        return client.send(builder.apply(HttpRequest.newBuilder().uri(uri)).build(), HttpResponse.BodyHandlers.ofString());
    }

    public static HttpResponse<String> get(HttpClient client, URI uri) throws IOException, InterruptedException {
        return get(client, uri, builder -> builder);
    }

    public static HttpResponse<String> get(URI uri) throws IOException, InterruptedException {
        return get(DEFAULT, uri, builder -> builder);
    }


    public static HttpResponse<String> get(URI uri, Function<HttpRequest.Builder, HttpRequest.Builder> builder) throws IOException, InterruptedException {
        return get(DEFAULT, uri, builder);
    }


    public static CompletableFuture<HttpResponse<String>> getAsync(HttpClient client, URI uri, Function<HttpRequest.Builder, HttpRequest.Builder> builder) {
        return client.sendAsync(builder.apply(HttpRequest.newBuilder().uri(uri)).build(), HttpResponse.BodyHandlers.ofString());
    }
    public static CompletableFuture<HttpResponse<String>> getAsync(HttpClient client, URI uri) {
        return getAsync(client, uri, builder -> builder);
    }

    public static CompletableFuture<HttpResponse<String>> getAsync(URI uri) {
        return getAsync(DEFAULT, uri, builder -> builder);
    }

    public static CompletableFuture<HttpResponse<String>> getAsync(URI uri, Function<HttpRequest.Builder, HttpRequest.Builder> builder) {
        return getAsync(DEFAULT, uri, builder);
    }


    public static String getOrThrow(HttpClient client, URI uri, Function<HttpRequest.Builder, HttpRequest.Builder> builder) throws WebResultException {
        try {
            HttpResponse<String> response = get(client, uri, builder);
            if(response.statusCode() == 200) {
                return response.body();
            }else {
                throw new WebResultException(uri.toString(), response.body(), response.statusCode());
            }
        }catch (IOException | InterruptedException e) {
            throw new WebResultException(e);
        }
    }

    public static String postOrThrow(HttpClient client, URI url, String map, IContentType contentType, Function<HttpRequest.Builder, HttpRequest.Builder> builder) throws WebResultException {
        try {
            HttpResponse<String> response = post(client, url, map, contentType, builder);
            if(response.statusCode() == 200) {
                return response.body();
            }else {
                throw new WebResultException(url.toString(), response.body(), response.statusCode());
            }
        }catch (IOException | InterruptedException e) {
            throw new WebResultException(e);
        }
    }

    public static String postOrThrow(HttpClient client, URI url, String map, IContentType contentType) throws WebResultException {
        return postOrThrow(client, url, map, contentType, builder -> builder);
    }

    public static String postOrThrow(URI url, String dat, IContentType contentType) throws WebResultException {
        return postOrThrow(DEFAULT, url, dat, contentType, builder -> builder);
    }


    public static String getOrThrow(HttpClient client, URI uri) throws WebResultException {
        return getOrThrow(client, uri, builder -> builder);
    }

    public static String getOrThrow(URI uri) throws WebResultException {
        return getOrThrow(DEFAULT, uri, builder -> builder);
    }

    public static String getOrThrow(URI uri, Function<HttpRequest.Builder, HttpRequest.Builder> builder) throws WebResultException {
        return getOrThrow(DEFAULT, uri, builder);
    }

    public static CompletableFuture<String> getOrThrowAsync(HttpClient client, URI uri, Function<HttpRequest.Builder, HttpRequest.Builder> builder) throws WebResultException {
        return getAsync(client, uri, builder)
                .thenCompose(response1 -> {
                    if (response1.statusCode() == 200) {
                        return CompletableFuture.completedFuture(response1.body());
                    } else {
                        return CompletableFuture.failedFuture(new WebResultException(uri.toString(), response1.body(), response1.statusCode()));
                    }
                });
    }
    public static CompletableFuture<String> getOrThrowAsync(HttpClient client, URI uri) throws WebResultException {
        return getOrThrowAsync(client, uri, builder -> builder);
    }

    public static CompletableFuture<String> getOrThrowAsync(URI uri, Function<HttpRequest.Builder, HttpRequest.Builder> builder) throws WebResultException {
        return getOrThrowAsync(DEFAULT, uri, builder);
    }

    public static CompletableFuture<String> getOrThrowAsync(URI uri) throws WebResultException {
        return getOrThrowAsync(DEFAULT, uri);
    }

    public static String encode(String url) {
        return URLEncoder.encode(url, StandardCharsets.UTF_8);
    }


}

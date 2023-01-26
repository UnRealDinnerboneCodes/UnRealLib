package com.unrealdinnerbone.unreallib.web;


import com.unrealdinnerbone.unreallib.LogHelper;
import com.unrealdinnerbone.unreallib.apiutils.WebResultException;
import org.slf4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

public class HttpHelper {

    private static final Logger LOGGER = LogHelper.getLogger();

    protected final HttpClient client;

    public HttpHelper(HttpClient client) {
        this.client = client;
    }

    public HttpResponse<String> post(String url, String map, IContentType contentType) throws IOException, InterruptedException {
        HttpRequest request = createDefaultPostRequestBuilder(URI.create(url), map, contentType);
        return send(request);
    }

    public CompletableFuture<HttpResponse<String>> postAsync(String url, String map, IContentType contentType) {
        return createURI(url)
                .thenApply(uri -> createDefaultPostRequestBuilder(uri, map, contentType))
                .thenCompose(this::sendAsync);
    }



    public HttpResponse<String> get(String url) throws IOException, InterruptedException, IllegalArgumentException {
        return send(createDefaultGetRequestBuilder(URI.create(url)));
    }

    public CompletableFuture<HttpResponse<String>> getAsync(String url) {
        return createURI(url)
                .thenApply(this::createDefaultGetRequestBuilder)
                .thenCompose(this::sendAsync);
    }

    protected String getOrThrow(String url) throws WebResultException {
        try {
            HttpResponse<String> response = send(createDefaultGetRequestBuilder(URI.create(url)));
            if(response.statusCode() == 200) {
                return response.body();
            }else {
                throw new WebResultException(url, response.body(), response.statusCode());
            }
        }catch (IOException | InterruptedException e) {
            throw new WebResultException(e);
        }
    }



    public HttpResponse<String> send(HttpRequest request) throws IOException, InterruptedException {
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public CompletableFuture<HttpResponse<String>> sendAsync(HttpRequest request) {
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpRequest createDefaultPostRequestBuilder(URI url, String map, IContentType type) {
        return createDefaultRequestBuilder(url).POST(HttpRequest.BodyPublishers.ofString(map))
                .setHeader("Content-Type", type.getType()).build();
    }
    public HttpRequest createDefaultGetRequestBuilder(URI url) {
        return createDefaultRequestBuilder(url).GET().build();
    }

    public HttpRequest.Builder createDefaultRequestBuilder(URI uri) {
        return HttpRequest.newBuilder().uri(uri);
    }

    public CompletableFuture<URI> createURI(String url) {
        try {
            return CompletableFuture.completedFuture(URI.create(url));
        }catch (IllegalArgumentException e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    public static String encode(String url) {
        return URLEncoder.encode(url, StandardCharsets.UTF_8);
    }

    public enum ContentType implements IContentType {
        JSON("application/json"),
        FORM("application/x-www-form-urlencoded");

        private final String type;

        ContentType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public interface IContentType {
        String getType();
    }

}

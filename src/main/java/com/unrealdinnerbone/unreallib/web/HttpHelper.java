package com.unrealdinnerbone.unreallib.web;


import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class HttpHelper {

    public static final HttpHelper DEFAULT = new HttpHelper(HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build(), "Java-HttpClient");

    protected final HttpClient client;
    protected final String userAgent;

    public HttpHelper(HttpClient client, String userAgent) {
        this.client = client;
        this.userAgent = userAgent;
    }

    //"application/x-www-form-urlencoded"
    public HttpResponse<String> post(String url, String map, IContentType contentType) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(map))
                .uri(URI.create(url))
                .setHeader("User-Agent", userAgent)
                .header("Content-Type", contentType.getType())
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }


    public HttpResponse<String> get(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .setHeader("User-Agent", userAgent)
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static String encode(String url) {
        return URLEncoder.encode(url, StandardCharsets.UTF_8);
    }


    public enum ContentType implements IContentType{
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

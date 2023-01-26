package com.unrealdinnerbone.unreallib.web;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class BasicHttpHelper extends HttpHelper {
    protected final String userAgent;

    public static final HttpHelper DEFAULT = new BasicHttpHelper(HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build(), "Java-HttpClient");

    public BasicHttpHelper(HttpClient client, String userAgent) {
        super(client);
        this.userAgent = userAgent;
    }

    public HttpRequest.Builder createDefaultRequestBuilder(URI uri) {
        return super.createDefaultRequestBuilder(uri)
                .setHeader("User-Agent", userAgent);
    }

}

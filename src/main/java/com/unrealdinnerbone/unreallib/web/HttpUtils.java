package com.unrealdinnerbone.unreallib.web;

import com.unrealdinnerbone.unreallib.JsonUtil;
import com.unrealdinnerbone.unreallib.Pair;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HttpUtils {

    private static final Logger LOOGER = LoggerFactory.getLogger(HttpUtils.class);

    private static final CloseableHttpClient httpClient;

    static {
        BasicCookieStore cookieStore = new BasicCookieStore();
        httpClient = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
    }

    public static BasicClientCookie getCookie(String name, String value, String domain) {
        BasicClientCookie cookie = new BasicClientCookie(name, value);
        cookie.setDomain(domain);
        cookie.setPath("/");
        return cookie;
    }

    public static String post(String url, Object dataMap, Header header) {
        try {
            HttpPost httpPost = new HttpPost(url);
            if (header != null) {
                httpPost.addHeader(header);
            }
            if (dataMap != null) {
                httpPost.setEntity(createStringEntity(dataMap));
            }
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            } else {
                LOOGER.error("Post to {} was null", url);
            }
        } catch (Exception e) {
            LOOGER.error("Error while posting", e);
        }
        return null;
    }

    public static String get(String url, List<Pair<String, String>> headers) {
        try {
            HttpGet httpGet = new HttpGet(url);
            if (headers != null) {
                headers.forEach(header -> httpGet.addHeader(header.key(), header.value()));
            }
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            } else {
                LOOGER.error("Get to {} was null", url);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOOGER.error("Error on get", e);
        }
        return null;
    }

    public static String get(String url) {
        return get(url, null);
    }

    private static StringEntity createStringEntity(Object hashMap) {
        return new StringEntity(JsonUtil.GSON.toJson(hashMap), ContentType.APPLICATION_JSON);
    }

}

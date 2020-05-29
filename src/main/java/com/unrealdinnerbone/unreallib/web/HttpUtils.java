package com.unrealdinnerbone.unreallib.web;

import com.unrealdinnerbone.unreallib.JsonUtil;
import com.unrealdinnerbone.unreallib.Pair;
import lombok.extern.slf4j.Slf4j;
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
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class HttpUtils {
    private static final CloseableHttpClient httpClient;

    static {
        BasicCookieStore cookieStore = new BasicCookieStore();

        cookieStore.addCookie(getCookie("AWSALB", "Xtyl0N1KFDMO5yoYVXD+yzJLYnAZpnmLpRr/FxB6WVm2Artczv1an4wUZ1nspoZp6Vv7i1VAbM7T8m9AC5t48UzUzoYK0Jkx3sxroeuxBPKkFqJtx9dKBfsOhifO", "www.curseforge.com"));
        cookieStore.addCookie(getCookie("AWSALBCORS", "Xtyl0N1KFDMO5yoYVXD+yzJLYnAZpnmLpRr/FxB6WVm2Artczv1an4wUZ1nspoZp6Vv7i1VAbM7T8m9AC5t48UzUzoYK0Jkx3sxroeuxBPKkFqJtx9dKBfsOhifO", "www.curseforge.com"));
        cookieStore.addCookie(getCookie("Unique_ID_v2", "e241edd47f9c42b8bbc4c9a673f0f8f8", "curseforge.com"));
        cookieStore.addCookie(getCookie("__cf_bm", "2d39b34cc56f3cf115cb477cd726ff5711b734da", "curseforge.com"));
        cookieStore.addCookie(getCookie("__cfduid", "d2bfa086b475de9ab71c7182a3370af0c1586793220", "www.curseforge.com"));
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
                log.error("Post to {} was null", url);
            }
        } catch (Exception e) {
            log.error("Error while posting", e);
        }
        return null;
    }

    public static String get(String url, List<Pair<String, String>> headers) {
        try {
            HttpGet httpGet = new HttpGet(url);
            if (headers != null) {
                for (Pair<String, String> header : headers) {
                    httpGet.addHeader(header.getA(), header.getB());
                }
//                Arrays.stream(header).forEach(httpGet::addHeader);
            }
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            } else {
                log.error("Get to {} was null", url);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error on get", e);
        }
        return null;
    }

    public static String get(String url) {
        String value = get(url, null);
        return value;
    }

    private static StringEntity createStringEntity(Object hashMap) {
        return new StringEntity(JsonUtil.getBasicGson().toJson(hashMap), ContentType.APPLICATION_JSON);
    }

}

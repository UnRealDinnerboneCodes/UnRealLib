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
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class HttpUtils {
    private static final CloseableHttpClient httpClient = HttpClients.createDefault();

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

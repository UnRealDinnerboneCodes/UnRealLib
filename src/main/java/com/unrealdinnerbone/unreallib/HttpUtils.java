package com.unrealdinnerbone.unreallib;

import com.unrealdinnerbone.unreallib.log.LogHelper;
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
import java.util.logging.Logger;

import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtils
{
    private static final CloseableHttpClient httpClient = HttpClients.createDefault();
    private static Logger LOGGER = LogHelper.getLogger(HttpUtils.class);

    public static String post(String url, Object dataMap, Header header)  {
        try {
            HttpPost httpPost = new HttpPost(url);
            if(header != null) {
                httpPost.addHeader(header);
            }
            if(dataMap != null) {
                httpPost.setEntity(createStringEntity(dataMap));
            }
            HttpResponse response = httpClient.execute(httpPost );
            HttpEntity entity = response.getEntity();
            if(entity != null) {
                return EntityUtils.toString(entity);
            }else {
               LOGGER.warning(StringUtils.replace("post to {0} was null", url));
            }
        }catch (Exception e) {
            LogHelper.logExpection(LOGGER, e);
        }
        return null;
    }

    public static String get(String url, Header header)  {
        try {
            HttpGet httpGet = new HttpGet(url);
            if(header != null) {
                httpGet.addHeader(header);
            }
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if(entity != null) {
                return EntityUtils.toString(entity);
            }else {
                LOGGER.warning(StringUtils.replace("get to {0} was null", url));
            }
        }catch (Exception e) {
            LogHelper.logExpection(LOGGER, e);
        }
        return null;
    }

    private static StringEntity createStringEntity(Object hashMap) {
        return new StringEntity(JsonHelper.getBasicGson().toJson(hashMap), ContentType.APPLICATION_JSON);
    }

    public static URL createURL(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            LogHelper.logExpection(LOGGER, e);
        }
        return null;
    }
}

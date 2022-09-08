package com.unrealdinnerbone.unreallib.apiutils;

import com.unrealdinnerbone.unreallib.web.HttpUtils;

import java.io.IOException;
import java.net.http.HttpResponse;

public class APIUtils {


    public static <T> ReturnResult<T> get(Class<T> tClass, String urlData) {
        try {
            HttpResponse<String> response = HttpUtils.get(urlData);
            if(response.statusCode() == 200) {
                return new ReturnResult<>(response.body(), tClass);
            }else {
                return ReturnResult.createException(new IllegalStateException("Received Status Code: " + response.statusCode() + " " + response.body()));
            }
        } catch (IOException | InterruptedException e) {
            return ReturnResult.createException(e);
        }
    }

}

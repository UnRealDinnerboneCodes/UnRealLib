package com.unrealdinnerbone.unreallib.apiutils;

import com.unrealdinnerbone.unreallib.json.JsonParseException;
import com.unrealdinnerbone.unreallib.web.HttpHelper;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.http.HttpResponse;

public class APIUtils {



    @NotNull
    public static <T> IReturnResult<T> get(HttpHelper httpHelper, Class<T> tClass, String urlData) {
        try {
            HttpResponse<String> response = httpHelper.get(urlData);
            if(response.statusCode() == 200) {
                return new JsonResult<>(response.body(), tClass);
            }else {
                return IReturnResult.createException(new JsonParseException(new IllegalStateException("Received Status Code: " + response.statusCode() + " " + response.body())));
            }
        } catch (IOException | InterruptedException e) {
            return IReturnResult.createException(new JsonParseException(e));
        }
    }
    @NotNull
    public static <T> IReturnResult<T> get(Class<T> tClass, String urlData) {
       return get(HttpHelper.DEFAULT, tClass, urlData);
    }

}

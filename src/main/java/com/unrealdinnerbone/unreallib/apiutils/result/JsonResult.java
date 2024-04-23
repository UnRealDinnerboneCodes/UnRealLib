package com.unrealdinnerbone.unreallib.apiutils.result;

import com.unrealdinnerbone.unreallib.exception.WebResultException;
import com.unrealdinnerbone.unreallib.json.JsonUtil;
import com.unrealdinnerbone.unreallib.json.api.IJsonParser;
import com.unrealdinnerbone.unreallib.json.exception.JsonParseException;

import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class JsonResult<T> implements IResult<T> {

    private final IResult<HttpResponse<String>> result;
    private final Class<T> tClass;
    private final IJsonParser jsonParser;

    public JsonResult(IResult<HttpResponse<String>> result, Class<T> tClass, IJsonParser jsonParser) {
        this.result = result;
        this.tClass = tClass;
        this.jsonParser = jsonParser;
    }

    public JsonResult(IResult<HttpResponse<String>> result, Class<T> tClass) {
        this(result, tClass, JsonUtil.DEFAULT);
    }


    @Override
    //Todo support for other status codes
    public T getNow() throws WebResultException, JsonParseException {
        String body = result.getNow().body();
        if (result.getNow().statusCode() == 200) {
            return jsonParser.parse(tClass, body);
        } else {
            throw new WebResultException(result.getNow().uri().toString(), body, result.getNow().statusCode());
        }
    }

    @Override
    //Todo support for other status codes
    public CompletableFuture<T> get() {
        return result.get().thenApply(response -> {
            if (response.statusCode() == 200) {
                return jsonParser.parse(tClass, response.body());
            } else {
                throw new WebResultException(response.uri().toString(), response.body(), response.statusCode());
            }
        });
    }
}

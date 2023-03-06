package com.unrealdinnerbone.unreallib.exception;


public class WebResultException extends RuntimeException {

    public WebResultException(String url, String body, int code) {
        super(url + " returned an " + code + " with body: " + body);
    }

    public WebResultException(Exception e) {
        super(e);
    }

}

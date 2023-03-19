package com.unrealdinnerbone.unreallib.exception;


public class WebResultException extends RuntimeException {

    private final int code;
    public WebResultException(String url, String body, int code) {
        super(url + " returned an " + code + " with body: " + body);
        this.code = code;
    }

    public WebResultException(String message, int code) {
        super(message);
        this.code = code;
    }

    public WebResultException(Exception e) {
        super(e);
        this.code = 0;
    }

    public int getCode() {
        return code;
    }
}

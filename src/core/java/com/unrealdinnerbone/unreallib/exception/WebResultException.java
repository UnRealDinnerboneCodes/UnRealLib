package com.unrealdinnerbone.unreallib.exception;


public class WebResultException extends RuntimeException {

    private final int code;
    private final String body;
    public WebResultException(String url, String theBody, int code) {
        super(url + " returned an " + code + " with body: " + theBody);
        this.body = theBody;
        this.code = code;
    }

    public WebResultException(String message, int code) {
        super(message);
        this.body = message;
        this.code = code;
    }

    public WebResultException(Exception e) {
        super(e);
        this.body = e.getMessage();
        this.code = 0;
    }

    public int getCode() {
        return code;
    }

    public String getBody() {
        return body;
    }
}

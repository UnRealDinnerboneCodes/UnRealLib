package com.unrealdinnerbone.unreallib.json.api;

public class JsonParseException extends RuntimeException {

    public JsonParseException(Exception e) {
        super(e.getCause() != null ? e.getCause() : e);
    }

    public JsonParseException(String message) {
        super(message);
    }
}

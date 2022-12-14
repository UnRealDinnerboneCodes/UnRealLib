package com.unrealdinnerbone.unreallib.json.api;

public class JsonParseException extends Exception{

    public JsonParseException(Exception e) {
        super(e.getCause() != null ? e.getCause() : e);
    }
}

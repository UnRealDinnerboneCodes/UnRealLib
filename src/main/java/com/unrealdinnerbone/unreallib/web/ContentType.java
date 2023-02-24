package com.unrealdinnerbone.unreallib.web;

public enum ContentType implements IContentType {

    JSON("application/json"),
    FORM("application/x-www-form-urlencoded");

    private final String type;

    ContentType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

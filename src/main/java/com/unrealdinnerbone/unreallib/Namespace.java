package com.unrealdinnerbone.unreallib;

public record Namespace(String key, String value)  {

    @Override
    public String toString() {
        return key() + ":" + value();
    }
}

package com.unrealdinnerbone.unreallib;

public record Namespace(String key, String value)  {

    public Namespace(String s) {
        this(s.split(":")[0], s.split(":")[1]);
    }

    @Override
    public String toString() {
        return key() + ":" + value();
    }
}

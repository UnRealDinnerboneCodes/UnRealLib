package com.unrealdinnerbone.unreallib;

import java.util.Objects;

public record Namespace(String key, String value)  {

    @Override
    public String toString() {
        return key().toLowerCase() + ":" + value().toLowerCase();
    }

    public boolean is(Namespace namespace) {
        return namespace.equals(this);
    }

    public boolean is(String namespace) {
        String[] split = namespace.split(":");
        return split.length == 2 && this.key.equalsIgnoreCase(split[0]) && this.value.equalsIgnoreCase(split[1]);
    }

    public boolean is(String key, String value) {
        return this.key.equalsIgnoreCase(key) && this.value.equalsIgnoreCase(value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}

package com.unrealdinnerbone.unreallib;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record Namespace(String key, String value) implements Comparable<Namespace>  {

    public static Namespace of(String key, String value) {
        return new Namespace(key, value);
    }

    public static Namespace of(String namespace) {
        String[] split = namespace.split(":");
        if(split.length == 2) {
            return new Namespace(split[0], split[1]);
        }
        throw new IllegalArgumentException("Invalid namespace: " + namespace);
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
    public String toString() {
        return toString(":");
    }

    public String toString(String split) {
        return key().toLowerCase() + split + value().toLowerCase();
    }

    @Override
    public int hashCode() {
        return Objects.hash(key.toLowerCase(), value.toLowerCase());
    }

    @Override
    public int compareTo(@NotNull Namespace o) {
        return toString().compareTo(o.toString());
    }
}

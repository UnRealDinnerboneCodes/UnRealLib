package com.unrealdinnerbone.unreallib;

public record Pair<A, B>(A key, B value) {

    public static <A, B> Pair<A, B> of(A a, B b) {
        return new Pair<>(a, b);
    }

}

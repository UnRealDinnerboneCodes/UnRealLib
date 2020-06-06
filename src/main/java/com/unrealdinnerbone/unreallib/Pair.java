package com.unrealdinnerbone.unreallib;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Pair<A, B> {
    private final A key;
    private final B value;


    public static <A,B> Pair<A, B> of(A a, B b) {
        return new Pair<>(a, b);
    }
}

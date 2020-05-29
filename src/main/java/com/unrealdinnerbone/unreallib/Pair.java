package com.unrealdinnerbone.unreallib;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Pair<A, B> {
    private final A a;
    private final B b;


    public static <A,B> Pair<A, B> of(A a, B b) {
        return new Pair<>(a, b);
    }
}

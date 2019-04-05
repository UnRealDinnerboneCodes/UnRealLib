package com.unrealdinnerbone.unreallib;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureUtils {

    public static <T> T get(CompletableFuture<T> get) {
        try {
            return get.get();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

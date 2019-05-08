package com.unrealdinnerbone.unreallib;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class CompletableFutureUtil {

    public static <T> T get(CompletableFuture<T> completableFuture) {
        try {
            return completableFuture.get();
        }catch (InterruptedException | ExecutionException e) {
            log.error("Error while running completable future", e);
            return null;
        }
    }
}

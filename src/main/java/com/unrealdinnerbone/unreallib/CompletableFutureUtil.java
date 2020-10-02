package com.unrealdinnerbone.unreallib;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class CompletableFutureUtil {

    public static <T> T get(CompletableFuture<T> completableFuture) {
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error while running completable future", e);
            return null;
        }
    }

    public static <T> CompletableFuture<List<T>> join(List<CompletableFuture<T>> executionPromises) {
        return join(executionPromises.toArray(CompletableFuture[]::new));
    }

    public static <T> CompletableFuture<List<T>> join(CompletableFuture<T>... executionPromises) {
        CompletableFuture<Void> joinedPromise = CompletableFuture.allOf(executionPromises);
        return joinedPromise.thenApply(voit -> Arrays.stream(executionPromises).map(CompletableFuture::join).collect(Collectors.toList()));
    }


}

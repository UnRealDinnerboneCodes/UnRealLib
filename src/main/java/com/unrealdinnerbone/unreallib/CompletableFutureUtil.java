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

    public static CompletableFuture<?> join(CompletableFuture<?>... executionPromises) {
        CompletableFuture<Void> joinedPromise = CompletableFuture.allOf(executionPromises);
        return joinedPromise.thenApply(voit -> Arrays.stream(executionPromises).map(CompletableFuture::join).collect(Collectors.toList()));
    }


}

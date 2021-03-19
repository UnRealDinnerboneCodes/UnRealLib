//package com.unrealdinnerbone.unreallib;
//
//import java.util.Arrays;
//import java.util.concurrent.CompletableFuture;
//import java.util.stream.Collectors;
//
//public class CompletableFutureUtil {
//
//    public static CompletableFuture<?> join(CompletableFuture<?>... executionPromises) {
//        CompletableFuture<Void> joinedPromise = CompletableFuture.allOf(executionPromises);
//        return joinedPromise.thenApply(voit -> Arrays.stream(executionPromises).map(CompletableFuture::join).collect(Collectors.toList()));
//    }
//
//
//}

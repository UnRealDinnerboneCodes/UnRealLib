package com.unrealdinnerbone.unreallib;

import com.unrealdinnerbone.unreallib.api.ILogger;
import com.unrealdinnerbone.unreallib.log.LogHelper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CompletableFutureUtil {

    private final static ILogger iLogger = LogHelper.getLogger(CompletableFutureUtil.class);

    public static <T> T get(CompletableFuture<T> get) {
        try {
            return get.get();
        }catch (InterruptedException | ExecutionException e) {
            iLogger.error("Error while running completable future", e);
            return null;
        }
    }
}

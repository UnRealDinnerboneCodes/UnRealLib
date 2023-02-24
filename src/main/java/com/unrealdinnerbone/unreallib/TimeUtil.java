package com.unrealdinnerbone.unreallib;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class TimeUtil {
    public static Instant utcNow() {
        return Instant.now().atZone(ZoneId.of("UTC")).toInstant();
    }

    public static boolean isWithinFrom(Instant from, Instant to, long amount, ChronoUnit unit) {
        long theAmount = ChronoUnit.HOURS.between(from, to);
        return theAmount <= amount && theAmount >= 0;
    }
}

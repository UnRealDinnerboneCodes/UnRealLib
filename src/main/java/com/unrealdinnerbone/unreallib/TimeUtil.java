package com.unrealdinnerbone.unreallib;

import java.time.Instant;
import java.time.ZoneId;

public class TimeUtil {
    
    public static Instant utcNow() {
        return Instant.now().atZone(ZoneId.of("UTC")).toInstant();
    }
}

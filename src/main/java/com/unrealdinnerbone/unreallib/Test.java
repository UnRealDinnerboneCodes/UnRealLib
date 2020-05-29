package com.unrealdinnerbone.unreallib;

import com.google.gson.annotations.SerializedName;
import com.unrealdinnerbone.unreallib.file.FileHelper;
import com.unrealdinnerbone.unreallib.web.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Test
{
    private static final List<Long> knowSeeds = new ArrayList<>();

    public static void main(String[] args) {
        Arrays.stream(FileHelper.jsonFromString(HttpUtils.get("http://sevisapickyasshole.net/api/notverified"), JsonUtil.getBasicGson(), KnowSeed[].class)).map(knowSeed -> knowSeed.seed).forEach(knowSeeds::add);
            FileHelper.readAllLinesinFile(FileHelper.getOrCreateFile("words.txt")).parallelStream().forEach(s -> {
                if (knowSeeds.contains(getSeed(s))) {
                    log.info("Found match for {}", s);
                } else {
                    log.info("Not match for {} - {}", s, getSeed(s));
                }
            });
    }

    public static long getSeed(String text) {
        long l = 0L;
        if (!StringUtils.isEmpty(text)) {
            try {
                long m = Long.parseLong(text);
                if (m != 0L) {
                    l = m;
                }
            } catch (NumberFormatException var6) {
                l = (long)text.hashCode();
            }
        }
        return l;
    }

    public static class KnowSeed {
        private long seed;
    }
}

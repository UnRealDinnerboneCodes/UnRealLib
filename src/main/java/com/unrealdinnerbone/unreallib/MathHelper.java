package com.unrealdinnerbone.unreallib;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class MathHelper {

    public static int randomInt(int min, int max) {
        return randomInt(ThreadLocalRandom.current(), min, max);
    }

    public static int randomInt(Random random, int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public static boolean randomBoolean(Random random) {
        return random.nextBoolean();
    }
    public static boolean randomBoolean() {
        return randomBoolean(ThreadLocalRandom.current());
    }

    public static boolean randomBoolean(int chance) {
        return randomInt(0, 100) <= chance;
    }

    public static boolean isDisableByX(int number, int divisor) {
        return number % divisor == 0;
    }

    public static long getMinecraftTickTime(TimeUnit timeUnit, int amount) {
        return timeUnit.toSeconds(amount) * 20;
    }

}

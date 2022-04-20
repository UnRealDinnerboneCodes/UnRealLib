package com.unrealdinnerbone.unreallib;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class MathHelper {

    private static final int RANDOM = randomInt(0, 1);

    public static int randomInt(int min, int max) {
        return randomInt(ThreadLocalRandom.current(), min, max);
    }

    public static int randomInt(Random random, int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public static boolean randomBoolean() {
        return randomInt(0, 1) == RANDOM;
    }

    public static boolean isDisableByX(int number, int divisor) {
        return number % divisor == 0;
    }

    public static long getMinecraftTickTime(TimeUnit timeUnit, int amount) {
        return timeUnit.toSeconds(amount) * 20;
    }

}

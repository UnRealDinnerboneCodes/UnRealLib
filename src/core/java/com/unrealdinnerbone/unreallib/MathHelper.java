package com.unrealdinnerbone.unreallib;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class MathHelper {

    private static final int RANDOM = randomInt(0, 1);


    public static int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt((max - min) + 1) + min;
    }

    public static boolean randomYes() {
        return randomInt(0, 1) == RANDOM;
    }

    public static boolean isDisableByX(int number, int divisor) {
        return number % divisor == 0;
    }

    public static long getMinecraftTickTime(TimeUnit timeUnit, int amount) {
        return timeUnit.toSeconds(amount) * 20;
    }

}

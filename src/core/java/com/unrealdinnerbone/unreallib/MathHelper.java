package project.belboz.common.lib;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class MathHelper {

    public static final int RANDOM = randomInt(0, 1);

    public static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }

    public static int randomInt(int min, int max) {
        return getRandom().nextInt((max - min) + 1) + min;
    }

    public static boolean randomYes() {
        return randomInt(0, 1) == RANDOM;
    }

    public static boolean isDisableByX(int number, int divisor) {
        return number % divisor == 0;
    }
    
    public static long getTickTime(TimeUnit timeUnit, int amount) {
        return timeUnit.toSeconds(amount) * 20;
    }

}

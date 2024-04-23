package com.unrealdinnerbone.unreallib.list;

import com.unrealdinnerbone.unreallib.MathHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;

public class ArrayUtil {

    public static long[] conventLongSetToArray(Set<Long> longs) {
        List<Long> longsList = new ArrayList<>(longs);
        long[] longArray = new long[longsList.size()];
        for (int i = 0; i < longs.size(); i++) {
            longArray[i] = longsList.get(i);
        }
        return longArray;
    }

    public static <T> String listToString(List<T> tList) {
        return tList.stream()
                .map(T::toString)
                .collect(Collectors.joining("\n"));
    }

    public static <T> T getLastValue(@NotNull List<T> t) {
        return t.get(t.size() - 1);
    }

    @Nullable
    public static <T> T getRandomValue(Random random, List<T> tList) {
        return tList.isEmpty() ? null : tList.get(MathHelper.randomInt(random, 0, tList.size() - 1));
    }

    @Nullable
    public static <T> T getRandomValue(List<T> tList) {
        return getRandomValue(ThreadLocalRandom.current(), tList);
    }


    public static <T> Optional<T> getRandomValueOptionally(List<T> tList) {
        return Optional.ofNullable(getRandomValue(tList));
    }

    public static <T> T getRandomValueAndRemove(List<T> tList) {
        T t = getRandomValue(tList);
        if(t != null) {
            tList.remove(t);
        }
        return t;
    }

    public static <T> T getRandomValueAndRemove(Random random, List<T> tList) {
        T t = getRandomValue(random, tList);
        if(t != null) {
            tList.remove(t);
        }
        return t;
    }

    public static <T> Comparator<T> shuffle() {
        Map<Object, UUID> uniqueIds = new IdentityHashMap<>();
        return Comparator.comparing(e -> uniqueIds.computeIfAbsent(e, k -> UUID.randomUUID()));
    }


    public static <T> List<T> allOf(List<T>... lists) {
        return Arrays.stream(lists).flatMap(Collection::stream).collect(Collectors.toList());
    }

    public static List<Integer> getRandomInts(int min, int max, int amount, IntPredicate intPredicate) {
        List<Integer> integers = new ArrayList<>();
        ThreadLocalRandom.current().ints(min, max).filter(intPredicate).distinct().limit(amount).forEach(integers::add);
        return integers;
    }

    public static List<Integer> getRandomInts(int min, int max, int amount) {
        List<Integer> integers = new ArrayList<>();
        ThreadLocalRandom.current().ints(min, max).distinct().limit(amount).forEach(integers::add);
        return integers;
    }

    public static Integer getRandomInt(List<Integer> alreadyTaken, int min, int max) {
        int total = max - min;
        if(alreadyTaken.size() == total) {
            throw new RuntimeException("Cant add more!");
        }else {
            int random = MathHelper.randomInt(min, max);
            if(alreadyTaken.contains(random)) {
                return getRandomInt(alreadyTaken, min, max);
            }else {
                return random;
            }
        }
    }
}

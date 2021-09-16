package com.unrealdinnerbone.unreallib;

import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
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
        if (tList == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        tList.forEach(t -> builder.append(t.toString()).append("\n"));
        return builder.toString();
    }



    public static <A, B, T> T getValueOrElse(HashMap<A, B> hashMap, A a, Function<B, T> function, T t) {
        return hashMap.containsKey(a) ? function.apply(hashMap.get(a)) : t;
    }

    public static <T> T getLastValue(List<T> t) {
        return t.get(t.size() - 1);
    }

    @Nullable
    public static <T> T getRandomValue(List<T> tList) {
        return tList.size() == 0 ? null : tList.get(MathHelper.randomInt(0, tList.size() - 1));
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

}

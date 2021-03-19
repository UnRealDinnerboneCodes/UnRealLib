package com.unrealdinnerbone.unreallib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

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

    public static <T> String listToJsonString(List<T> tList) {
        if (tList == null) {
            return "{}";
        }
        return JsonUtil.GSON.toJson(tList);
    }

    public static <A, B, T> T getValueOrElse(HashMap<A, B> hashMap, A a, Function<B, T> function, T t) {
        return hashMap.containsKey(a) ? function.apply(hashMap.get(a)) : t;
    }

    public static <T> T getLastValue(List<T> t) {
        return t.get(t.size() - 1);
    }

}

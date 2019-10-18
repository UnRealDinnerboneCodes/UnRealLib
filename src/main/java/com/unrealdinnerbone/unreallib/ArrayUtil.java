package com.unrealdinnerbone.unreallib;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
            return null;
        }
        StringBuilder builder = new StringBuilder();
        tList.forEach(t -> builder.append(t.toString()).append("\n"));
        return builder.toString();
    }

    public static <T> T getLastValue(List<T> t) {
        return t.get(t.size() - 1);
    }

}

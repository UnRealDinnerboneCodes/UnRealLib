package com.unrealdinnerbone.unreallib;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ArrayUtils
{
    public static long[] conventLongSetToArray(Set<Long> longs) {
        List<Long> longsList = new ArrayList<>(longs);
        long[] longArray = new long[longsList.size()];
        for (int i = 0; i < longs.size(); i++) {
            longArray[i] = longsList.get(i);
        }
        return longArray;
    }
}

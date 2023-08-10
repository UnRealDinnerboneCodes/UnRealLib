package com.unrealdinnerbone.unreallib.list;

import java.util.Map;

public class Maps {
    public static <K, V> V putIfAbsent(Map<K, V> map, K key, V value) {
        if (!map.containsKey(key)) {
            map.put(key, value);
        }
        return map.get(key);
    }
}

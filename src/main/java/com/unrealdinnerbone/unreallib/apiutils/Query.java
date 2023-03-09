package com.unrealdinnerbone.unreallib.apiutils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public record Query(String query) {


    public static class Builder {

        private final Map<String, String> map;

        public Builder() {
            this.map = new HashMap<>();
        }

        public Builder withQuery(String key, String value) {
            map.put(key, value);
            return this;
        }

        public Query build(int minSize) {
            if (map.size() < minSize) {
                throw new IllegalArgumentException("Query is to small, needs to be at least " + minSize);
            } else {
                return new Query(map.entrySet().stream()
                        .map(entry -> entry.getKey() + "=" + entry.getValue())
                        .collect(Collectors.joining("&")));
            }
        }
    }
}
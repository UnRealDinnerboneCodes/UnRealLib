package com.unrealdinnerbone.unreallib.discord;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class EmbedObject {

    @Nullable private final String title;
    @Nullable private final String description;
    @Nullable private final String url;
    @Nullable private final Color color;

    @Nullable private final Footer footer;
    @Nullable private final String thumbnail;
    @Nullable private final String image;
    @Nullable private final Author author;
    private final List<Field> fields = new ArrayList<>();

    @AllArgsConstructor
    @Getter
    public static class Footer {
        private final String text;
        private final String iconUrl;
    }

    @AllArgsConstructor
    @Getter
    public static class Author {
        private final String name;
        private final String url;
        private final String iconUrl;
    }

    @AllArgsConstructor
    @Getter
    public static class Field {
        private final String name;
        private final String value;
        private final boolean inline;
    }
}

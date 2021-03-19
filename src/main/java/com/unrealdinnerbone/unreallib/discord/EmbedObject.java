package com.unrealdinnerbone.unreallib.discord;

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

    public record Footer(String text, String iconUrl) {}

    public record Author(String name, String url, String iconUrl) {}

    public record Field(String name, String value, boolean inline) {}
}

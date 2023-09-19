package com.unrealdinnerbone.unreallib.discord;

import com.unrealdinnerbone.unreallib.SimpleColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public record EmbedObject(
        @Nullable String title,
        @Nullable String description,
        @Nullable String url,
        @Nullable SimpleColor color,
        @Nullable Footer footer,
        @Nullable String thumbnail,
        @Nullable String image,
        @Nullable Author author,
        List<Field> fields) {

    public static EmbedObjectBuilder builder() {
        return new EmbedObjectBuilder();
    }

    public record Footer(String text, String iconUrl) {}
    public record Author(String name, String url, String iconUrl) {}
    public record Field(String name, String value, boolean inline) {}

    public static class EmbedObjectBuilder {
        @Nullable private String title;
        @Nullable private String description;
        @Nullable private String url;
        @Nullable private SimpleColor color;
        @Nullable private Footer footer;
        @Nullable private String thumbnail;
        @Nullable private String image;
        @Nullable private Author author;
        @NotNull private final List<Field> fields = new ArrayList<>();

        public EmbedObjectBuilder title(@Nullable String title) {
            this.title = title;
            return this;
        }

        public EmbedObjectBuilder description(@Nullable String description) {
            this.description = description;
            return this;
        }

        public EmbedObjectBuilder url(@Nullable String url) {
            this.url = url;
            return this;
        }

        public EmbedObjectBuilder color(@Nullable SimpleColor color) {
            this.color = color;
            return this;
        }

        public EmbedObjectBuilder footer(@Nullable Footer footer) {
            this.footer = footer;
            return this;
        }

        public EmbedObjectBuilder footer(@Nullable String text, @Nullable String iconUrl) {
            this.footer = new Footer(text, iconUrl);
            return this;
        }


        public EmbedObjectBuilder footer(@Nullable String text) {
            this.footer = new Footer(text, null);
            return this;
        }

        public EmbedObjectBuilder thumbnail(@Nullable String thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public EmbedObjectBuilder image(@Nullable String image) {
            this.image = image;
            return this;
        }

        public EmbedObjectBuilder author(@Nullable Author author) {
            this.author = author;
            return this;
        }

        public EmbedObjectBuilder author(@Nullable String name, @Nullable String url, @Nullable String iconUrl) {
            this.author = new Author(name, url, iconUrl);
            return this;
        }

        public EmbedObjectBuilder author(@Nullable String name, @Nullable String url) {
            this.author = new Author(name, url, null);
            return this;
        }

        public EmbedObjectBuilder author(@Nullable String name) {
            this.author = new Author(name, null, null);
            return this;
        }

        public EmbedObjectBuilder field(@NotNull Field field) {
            this.fields.add(field);
            return this;
        }

        public EmbedObjectBuilder field(@NotNull String name, @NotNull String value, boolean inline) {
            this.fields.add(new Field(name, value, inline));
            return this;
        }

        public EmbedObject build() {
            return new EmbedObject(title, description, url, color, footer, thumbnail, image, author, fields);
        }
    }
}

package com.unrealdinnerbone.unreallib.discord;

import com.unrealdinnerbone.unreallib.web.BasicHttpHelper;
import com.unrealdinnerbone.unreallib.web.HttpHelper;

import java.awt.Color;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class DiscordWebhook {

    private final String url;
    private final List<EmbedObject> embeds = new ArrayList<>();
    private String content;
    private String username;
    private String avatarUrl;
    private boolean tts;

    private DiscordWebhook(String url) {
        this.url = url;
    }

    public static DiscordWebhook of(String url) {
        return new DiscordWebhook(url);
    }

    public DiscordWebhook addEmbed(EmbedObject embed) {
        this.embeds.add(embed);
        return this;
    }

    public DiscordWebhook setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public DiscordWebhook setContent(String content)
    {
        this.content = content;
        return this;
    }

    public DiscordWebhook setTts(boolean tts) {
        this.tts = tts;
        return this;
    }

    public DiscordWebhook setUsername(String username) {
        this.username = username;
        return this;
    }

    public HttpResponse<String> execute() throws IOException, InterruptedException {
        if (this.content == null && this.embeds.isEmpty()) {
            throw new IllegalArgumentException("Set content or add at least one EmbedObject");
        }

        JSONObject json = new JSONObject();

        json.put("content", this.content);
        json.put("username", this.username);
        json.put("avatar_url", this.avatarUrl);
        json.put("tts", this.tts);

        if (!this.embeds.isEmpty()) {
            List<JSONObject> embedObjects = new ArrayList<>();

            for (EmbedObject embed : this.embeds) {
                JSONObject jsonEmbed = new JSONObject();

                jsonEmbed.put("title", embed.title());
                jsonEmbed.put("description", embed.description());
                jsonEmbed.put("url", embed.url());

                if (embed.color() != null) {
                    Color color = embed.color();
                    int rgb = color.getRed();
                    rgb = (rgb << 8) + color.getGreen();
                    rgb = (rgb << 8) + color.getBlue();

                    jsonEmbed.put("color", rgb);
                }

                EmbedObject.Footer footer = embed.footer();
                String image = embed.image();
                String thumbnail = embed.thumbnail();
                EmbedObject.Author author = embed.author();
                List<EmbedObject.Field> fields = embed.fields();

                if (footer != null) {
                    JSONObject jsonFooter = new JSONObject();

                    jsonFooter.put("text", footer.text());
                    jsonFooter.put("icon_url", footer.iconUrl());
                    jsonEmbed.put("footer", jsonFooter);
                }

                if (image != null) {
                    JSONObject jsonImage = new JSONObject();

                    jsonImage.put("url", image);
                    jsonEmbed.put("image", jsonImage);
                }

                if (thumbnail != null) {
                    JSONObject jsonThumbnail = new JSONObject();

                    jsonThumbnail.put("url", thumbnail);
                    jsonEmbed.put("thumbnail", jsonThumbnail);
                }

                if (author != null) {
                    JSONObject jsonAuthor = new JSONObject();

                    jsonAuthor.put("name", author.name());
                    jsonAuthor.put("url", author.url());
                    jsonAuthor.put("icon_url", author.iconUrl());
                    jsonEmbed.put("author", jsonAuthor);
                }

                List<JSONObject> jsonFields = new ArrayList<>();
                for (EmbedObject.Field field : fields) {
                    JSONObject jsonField = new JSONObject();

                    jsonField.put("name", field.name());
                    jsonField.put("value", field.value());
                    jsonField.put("inline", field.inline());

                    jsonFields.add(jsonField);
                }

                jsonEmbed.put("fields", jsonFields.toArray());
                embedObjects.add(jsonEmbed);
            }

            json.put("embeds", embedObjects.toArray());
        }
        return BasicHttpHelper.DEFAULT.post(this.url, json.toString(), HttpHelper.ContentType.JSON);
    }

}

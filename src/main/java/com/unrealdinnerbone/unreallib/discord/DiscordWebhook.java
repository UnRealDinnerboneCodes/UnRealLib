package com.unrealdinnerbone.unreallib.discord;

import com.unrealdinnerbone.unreallib.json.JsonUtil;
import com.unrealdinnerbone.unreallib.web.ContentType;
import com.unrealdinnerbone.unreallib.web.HttpHelper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class DiscordWebhook {

    private final List<EmbedObject> embeds = new ArrayList<>();
    private String content;
    private String username;
    private String avatarUrl;
    private boolean tts;

    private DiscordWebhook() {}

    public static DiscordWebhook builder() {
        return new DiscordWebhook();
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

    public HttpResponse<String> post(String url) throws IOException, InterruptedException {
        if (this.content == null && this.embeds.isEmpty()) {
            throw new IllegalArgumentException("Set content or add at least one EmbedObject");
        }
        String json = JsonUtil.DEFAULT.toJson(DiscordWebhook.class, this);
        return HttpHelper.post(HttpHelper.DEFAULT, URI.create(url), json, ContentType.JSON);
    }

}

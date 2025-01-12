package com.unrealdinnerbone.unreallib.discord;

import com.unrealdinnerbone.unreallib.exception.WebResultException;
import com.unrealdinnerbone.unreallib.json.JsonUtil;
import com.unrealdinnerbone.unreallib.web.ContentType;
import com.unrealdinnerbone.unreallib.web.HttpHelper;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class DiscordWebhook {

    private final List<EmbedObject> embeds = new ArrayList<>();

    private List<Components> components = null;
    private String content;
    private String username;
    private String avatar_url;

    private DiscordWebhook() {}

    public static DiscordWebhook builder() {
        return new DiscordWebhook();
    }

    public DiscordWebhook addEmbed(EmbedObject embed) {
        this.embeds.add(embed);
        return this;
    }

    public DiscordWebhook setAvatarUrl(String avatarUrl) {
        this.avatar_url = avatarUrl;
        return this;
    }

    public DiscordWebhook setContent(String content) {
        this.content = content;
        return this;
    }

    public DiscordWebhook withComponents(List<Component> components) {
        if(this.components == null) {
            this.components = new ArrayList<>();
        }
        this.components.add(new Components(1, components.toArray(new Component[0])));
        return this;
    }


    public DiscordWebhook setUsername(String username) {
        this.username = username;
        return this;
    }

    public String post(String url) throws WebResultException, IllegalStateException {
        if (this.content == null && this.embeds.isEmpty()) {
            throw new IllegalStateException("Set content or add at least one EmbedObject");
        }
        String json = asJson();
        return HttpHelper.postOrThrow(URI.create(url), json, ContentType.JSON);
    }

    public String asJson() {
        return JsonUtil.DEFAULT.toJson(this);
    }


    public static record Components(int type, Component[] components) {}

}

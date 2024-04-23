package com.unrealdinnerbone.unreallib.discord;

import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Experimental
public interface Component
{

    static Component url(String label, String url) {
        return new URL(5, label, url, false, 2);
    }

    public record URL(int style, String label, String url, boolean disabled, int type) implements Component {}

}

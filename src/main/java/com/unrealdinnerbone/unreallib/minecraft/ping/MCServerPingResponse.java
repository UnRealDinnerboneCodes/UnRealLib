package com.unrealdinnerbone.unreallib.minecraft.ping;

import com.unrealdinnerbone.unreallib.json.api.DataString;

public record MCServerPingResponse(@DataString String description, Players players, Version version, String favicon) {

    public record Players(int max, int online) {}
    public record Version(String name, int protocol) {}
}


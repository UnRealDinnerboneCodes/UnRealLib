module com.unrealdinnerbone.unreallib.main {
    requires transitive com.unrealdinnerbone.unreallib.json;
    requires transitive com.unrealdinnerbone.unreallib.core;
    exports com.unrealdinnerbone.unreallib.apiutils;
    exports com.unrealdinnerbone.unreallib.minecraft.ping;
    exports com.unrealdinnerbone.unreallib.discord;
    exports com.unrealdinnerbone.unreallib.apiutils.result;
}

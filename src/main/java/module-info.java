module com.unrealdinnerbone.unreallib.main {
    requires org.jetbrains.annotations;
    requires java.net.http;
    requires com.google.gson;
    requires org.slf4j;
    exports com.unrealdinnerbone.unreallib.apiutils;
    exports com.unrealdinnerbone.unreallib.minecraft.ping;
    exports com.unrealdinnerbone.unreallib.discord;
    exports com.unrealdinnerbone.unreallib.apiutils.result;
    exports com.unrealdinnerbone.unreallib.json;
    exports com.unrealdinnerbone.unreallib.json.api;
    exports com.unrealdinnerbone.unreallib;
    exports com.unrealdinnerbone.unreallib.exception;
    exports com.unrealdinnerbone.unreallib.json.exception;
    exports com.unrealdinnerbone.unreallib.web;
}

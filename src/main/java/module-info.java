module com.unrealdinnerbone.unreallib.main {
    requires org.jetbrains.annotations;
    requires java.net.http;
    requires com.google.gson;
    requires org.slf4j;
    exports com.unrealdinnerbone.unreallib.apiutils;
    exports com.unrealdinnerbone.unreallib.minecraft.ping;
    exports com.unrealdinnerbone.unreallib.discord;
    exports com.unrealdinnerbone.unreallib.apiutils.result;
}

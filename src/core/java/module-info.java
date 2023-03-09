module com.unrealdinnerbone.unreallib.core {
    requires transitive org.jetbrains.annotations;
    requires transitive java.net.http;
    requires transitive org.slf4j;
    requires transitive java.desktop;
    exports com.unrealdinnerbone.unreallib;
    exports com.unrealdinnerbone.unreallib.either;
    exports com.unrealdinnerbone.unreallib.exception;
    exports com.unrealdinnerbone.unreallib.file;
    exports com.unrealdinnerbone.unreallib.list;
    exports com.unrealdinnerbone.unreallib.web;
    exports com.unrealdinnerbone.unreallib.registry;
    opens com.unrealdinnerbone.unreallib.either to com.google.gson;
}
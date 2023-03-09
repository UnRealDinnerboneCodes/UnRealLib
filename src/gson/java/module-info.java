module com.unrealdinnerbone.unreallib.json {
    requires com.google.gson;
    requires transitive com.unrealdinnerbone.unreallib.core;
    exports com.unrealdinnerbone.unreallib.json;
    exports com.unrealdinnerbone.unreallib.json.api;
    exports com.unrealdinnerbone.unreallib.json.exception;
}
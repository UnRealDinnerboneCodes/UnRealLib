module com.unrealdinnerbone.unreallib.json {
    requires com.squareup.moshi.moshi;
    requires okio;
    requires transitive com.unrealdinnerbone.unreallib.core;
    exports com.unrealdinnerbone.unreallib.json;
    exports com.unrealdinnerbone.unreallib.json.api;
    exports com.unrealdinnerbone.unreallib.json.moshi.parser;
    exports com.unrealdinnerbone.unreallib.json.moshi;
    exports com.unrealdinnerbone.unreallib.json.exception;
}
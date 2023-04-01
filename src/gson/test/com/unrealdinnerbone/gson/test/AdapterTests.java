package com.unrealdinnerbone.gson.test;

import com.unrealdinnerbone.unreallib.Namespace;
import com.unrealdinnerbone.unreallib.either.Either;
import com.unrealdinnerbone.unreallib.json.JsonUtil;
import com.unrealdinnerbone.unreallib.json.api.JsonRegistry;
import com.unrealdinnerbone.unreallib.json.api.JsonString;
import com.unrealdinnerbone.unreallib.json.gson.GsonParser;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.time.Instant;

public class AdapterTests {

    private final GsonParser parser = new GsonParser(builder -> builder);

    @Test
    public void testJsonString() {
        String someTestyJson = "{\"list\":[\"test\",\"test2\",\"test3\"]}";
        JsonString jsonString = parser.parse(JsonString.class, someTestyJson);
        Assert.assertEquals(someTestyJson, jsonString.json());
    }

    @Test
    public void testEither() {
        Either<String, Integer> either = Either.left("Test");
        String json = parser.toJson(either);
        Assert.assertEquals("\"Test\"", json);
        Either<String, Integer> eitherRight = Either.right(1);
        String jsonRight = parser.toJson(eitherRight);
        Assert.assertEquals("1", jsonRight);
    }

    @Test
    public void testNamespace() {
        String json = parser.toJson(Namespace.of("minecraft", "test"));
        Assert.assertEquals("\"minecraft:test\"", json);
    }

    @Test
    public void testEnum() {
        String json = parser.toJson(IDTestEnum.ONE);
        Assert.assertEquals("1", json);
    }

    @Test
    public void registryTest() {
        JsonRegistry<TestRegistryObject> registry = new JsonRegistry<>(TestRegistryObject::new, TestRegistryObject::value, TestRegistryObject.class, true);
        TestRegistryObject test = registry.register("test");
        TestRegistryObject test2 = registry.register("test2");
        TestRegistryObject test3 = registry.register("test3");

        String json = parser.toJson(test2);
        Assert.assertEquals("\"test2\"", json);
        TestRegistryObject testRegistryObject = parser.parse(TestRegistryObject.class, json);
        Assert.assertEquals(test2, testRegistryObject);

    }

    @Test
    public void testColor() {
        String json = parser.toJson(Color.RED);
        Assert.assertEquals("16711680", json);
        Color color = parser.parse(Color.class, json);
        Assert.assertEquals(Color.RED, color);
    }

    @Test
    public void testEitherFromJson() {
        Either<String, Integer> either = Either.left("Test");
        Either<String, Integer> asd = parser.getGson().fromJson("\"Test\"", either.getClass());

    }

    @Test
    public void testInstant() {
        Instant parse = JsonUtil.DEFAULT.parse(Instant.class, "\"2023-03-31T18:13:00-05:00\"");
        Assert.assertEquals(Instant.parse("2023-03-31T18:13:00-05:00"), parse);
    }



}

package com.unrealdinnerbone.gson.test;

import com.unrealdinnerbone.unreallib.Namespace;
import com.unrealdinnerbone.unreallib.either.Either;
import com.unrealdinnerbone.unreallib.json.api.IJsonParser;
import com.unrealdinnerbone.unreallib.json.api.JsonRegistry;
import com.unrealdinnerbone.unreallib.json.api.JsonString;
import com.unrealdinnerbone.unreallib.json.gson.GsonParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AdapterTests {

    private IJsonParser parser;

    @Before
    public void setup() {
        parser = new GsonParser(builder -> {
            return builder;
        });
    }

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



}

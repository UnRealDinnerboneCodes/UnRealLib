package json;

import com.unrealdinnerbone.unreallib.Namespace;
import com.unrealdinnerbone.unreallib.SimpleColor;
import com.unrealdinnerbone.unreallib.either.Either;
import com.unrealdinnerbone.unreallib.json.JsonUtil;
import com.unrealdinnerbone.unreallib.json.api.JsonRegistry;
import com.unrealdinnerbone.unreallib.json.api.JsonString;
import com.unrealdinnerbone.unreallib.json.gson.GsonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Map;

public class AdapterTests {

    private static final GsonParser parser = new GsonParser(builder -> builder);

    @Test
    public void testJsonString() {
        String someTestyJson = "{\"list\":[\"test\",\"test2\",\"test3\"]}";
        JsonString jsonString = parser.parse(JsonString.class, someTestyJson);
        Assertions.assertEquals(someTestyJson, jsonString.json());
    }

    @Test
    public void testEither() {
        Either<String, Integer> either = Either.left("Test");
        String json = parser.toJson(either);
        Assertions.assertEquals("\"Test\"", json);
        Either<String, Integer> eitherRight = Either.right(1);
        String jsonRight = parser.toJson(eitherRight);
        Assertions.assertEquals("1", jsonRight);
    }

    @Test
    public void testNamespace() {
        String json = parser.toJson(Namespace.of("minecraft", "test"));
        Assertions.assertEquals("\"minecraft:test\"", json);
    }

    @Test
    public void testEnum() {
        String json = parser.toJson(IDTestEnum.ONE);
        Assertions.assertEquals("1", json);
    }

    private static JsonRegistry<TestRegistryObject> registry;
    private static TestRegistryObject test;
    private static TestRegistryObject test2;
    private static TestRegistryObject test3;
    @BeforeAll
    public static void createRegistry() {
        registry =new JsonRegistry<>(TestRegistryObject::new, TestRegistryObject::value, TestRegistryObject.class, true);
        test = registry.register("test");
        test2 = registry.register("test2");
        test3 = registry.register("test3");
    }

    @Test
    public void registryTest() {
        String json = parser.toJson(test2);
        Assertions.assertEquals("\"test2\"", json);
        TestRegistryObject testRegistryObject = parser.parse(TestRegistryObject.class, json);
        Assertions.assertEquals(test2, testRegistryObject);
    }

    @Test
    public void registryMapTest() {
        Map<TestRegistryObject, String> testRegistryObjectStringMap = Map.of(test, "test");
        String json = parser.toJson(testRegistryObjectStringMap);
        Assertions.assertEquals("{\"test\":\"test\"}", json);
    }
    @Test
    public void testColor() {
        SimpleColor red = SimpleColor.fromRGB(255, 0, 0);
        String json = parser.toJson(red);
        Assertions.assertEquals("\"#ff0000\"", json);
        SimpleColor simpleColor = parser.parse(SimpleColor.class, json);
        Assertions.assertEquals(red, simpleColor);
    }

    @Test
    public void testEitherFromJson() {
        Either<String, Integer> either = Either.left("Test");
        Either<String, Integer> asd = parser.getGson().fromJson("\"Test\"", either.getClass());

    }

    @Test
    public void testInstant() {
        Instant parse = JsonUtil.DEFAULT.parse(Instant.class, "\"2023-03-31T18:13:00-05:00\"");
        Assertions.assertEquals(Instant.parse("2023-03-31T18:13:00-05:00"), parse);
    }



}

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.ToJson;
import com.unrealdinnerbone.unreallib.discord.JSONObject;
import com.unrealdinnerbone.unreallib.json.JsonUtil;
import com.unrealdinnerbone.unreallib.json.moshi.DataString;
import com.unrealdinnerbone.unreallib.json.moshi.RawJsonAdapter;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonTest
{


    public void testJson() throws Exception {

//        UUID uuid = UUID.randomUUID();

        String value = JsonUtil.DEFAULT.toJson(TestClass.class, new TestClass("cake", new Cake("s")));
        Object testClass = JsonUtil.DEFAULT.parse(Object.class, value);

        TestClass testClassTwo = JsonUtil.DEFAULT.parse(TestClass.class, value);

//        Assert.assertEquals(testClass.dTest, uuid);
//        Assert.assertNull(testClassTwo.dTest());

        System.out.println(JsonUtil.DEFAULT.toJson(Object.class, testClass));
        System.out.println(JsonUtil.DEFAULT.toJson(TestClass.class, testClassTwo));

    }

    public record TestClass(String dTest, Cake s) {

    }

    public record Cake(String s) {}

    public void testJsonObject() throws IOException {
        Moshi MOSHI = new Moshi.Builder().build();
        Path path = Path.of("tests", "jobject.json");
        System.out.println(MOSHI.adapter(Testy.class).fromJson(Files.readString(path)));
        System.out.println(MOSHI.adapter(TestKey.class).fromJsonValue(MOSHI.adapter(Testy.class).fromJson(Files.readString(path)).object));
    }

    public static record TestKey(String key) {}

    public static record Testy(String test, Object object) { }


    public void cake() throws IOException {
        Moshi MOSHI = new Moshi.Builder()
                .add(new TestAdapter())
                .build();


        World world = MOSHI.adapter(World.class).fromJson(Files.readString(Path.of("test.json")));
        String value = MOSHI.adapter(World.class).toJson(world);

        System.out.println(value);

        World world2 = MOSHI.adapter(World.class).fromJson(value);

        System.out.println(world2);
    }

    @Test
    public void testDataString() throws IOException {
        Moshi MOSHI = new Moshi.Builder()
                .add(new RawJsonAdapter())
                .build();

        System.out.println(MOSHI.adapter(DataTest.class).fromJson("{\n" +
                "    \"json\": {\n" +
                "        \"test\": [\n" +
                "            \"cake\"\n" +
                "        ],\n" +
                "        \"cheese\": {\n" +
                "            \"a\": \"b\",\n" +
                "            \"b\": \"a\"\n" +
                "        }\n" +
                "    }\n" +
                "}"));
    }

    public record DataTest(@DataString String json) {}


    @Test
    public void testFile() throws IOException {
    }
}

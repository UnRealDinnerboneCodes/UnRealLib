import com.unrealdinnerbone.unreallib.json.JsonUtil;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;
import java.util.UUID;

public class JsonTest
{


    @Test
    public void testJson() throws Exception {

        UUID uuid = UUID.randomUUID();

        TestClass testClass = JsonUtil.DEFAULT.parse(TestClass.class, "{\"dTest\":\"" + uuid + "\"}");

        TestClass testClassTwo = JsonUtil.DEFAULT.parse(TestClass.class, "{}");

        Assert.assertEquals(testClass.dTest, uuid);
        Assert.assertNull(testClassTwo.dTest());

    }

    public record TestClass(UUID dTest) {

    }
}

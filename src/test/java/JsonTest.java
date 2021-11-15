import com.unrealdinnerbone.unreallib.json.JsonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class JsonTest
{


    @Test
    public void testJson() throws Exception {

        TestClass testClass = JsonUtil.DEFAULT.parse(TestClass.class, "{\"dTest\":100}");

        TestClass testClassTwo = JsonUtil.DEFAULT.parse(TestClass.class, "{}");

//        Assert.assertNotNull(testClass.dTest());
//        Assert.assertNull(testClassTwo.dTest());

        Assert.assertEquals(JsonUtil.DEFAULT.parse(TestClassTwo.class, "{}").dTest(), Optional.empty());

    }


    public record TestClassTwo(Optional<Double> dTest) {

    }

    public record TestClass(Double dTest) {

    }
}

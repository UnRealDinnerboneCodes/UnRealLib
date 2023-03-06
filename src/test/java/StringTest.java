import com.unrealdinnerbone.unreallib.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class StringTest
{
    @Test
    public void testReplace() {
        Assert.assertEquals("Hello World", StringUtils.replace("Hello {0}", "World"));
    }
}

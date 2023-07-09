import com.unrealdinnerbone.unreallib.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringTest
{
    @Test
    public void testReplace() {
        Assertions.assertEquals("Hello World", StringUtils.replace("Hello {0}", "World"));
    }
}

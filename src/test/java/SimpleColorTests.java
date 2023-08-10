import com.unrealdinnerbone.unreallib.SimpleColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class SimpleColorTests
{
    @Test
    public void testSimpleColor() {
        Color color = new Color(255, 255, 255);
        SimpleColor simpleColor = SimpleColor.fromRGB(255, 255, 255);
        Assertions.assertEquals(color.getRed(), simpleColor.red());
        Assertions.assertEquals(color.getGreen(), simpleColor.green());
        Assertions.assertEquals(color.getBlue(), simpleColor.blue());
    }
}

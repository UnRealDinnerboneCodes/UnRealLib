import com.unrealdinnerbone.unreallib.ErrorableValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ErroableTest {

    @Test
    public void error() {
        ErrorableValue<Exception, String> errorableValue = ErrorableValue.of(new Exception("Test"));
        Assertions.assertTrue(errorableValue.hasException());
        Assertions.assertThrows(Exception.class, errorableValue::get);
    }

    @Test
    public void value() {
        ErrorableValue<Exception, String> errorableValue = ErrorableValue.of("Test");
        Assertions.assertFalse(errorableValue.hasException());
        try {
            Assertions.assertEquals("Test", errorableValue.get());
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    public void optional() {
        ErrorableValue<Exception, String> errorableValue = ErrorableValue.of("Test");
        Assertions.assertFalse(errorableValue.hasException());
        Assertions.assertEquals("Test", errorableValue.asOptional().orElse("Fail"));
    }

    @Test
    public void errorOptional() {
        ErrorableValue<Exception, String> errorableValue = ErrorableValue.of(new Exception("Test"));
        Assertions.assertTrue(errorableValue.hasException());
        Assertions.assertEquals("Fail", errorableValue.asOptional().orElse("Fail"));
    }



}

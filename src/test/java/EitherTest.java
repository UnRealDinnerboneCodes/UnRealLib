import com.unrealdinnerbone.unreallib.either.Either;
import org.junit.Assert;
import org.junit.Test;

public class EitherTest
{
    @Test
    public void test() {
        Either<String, Integer> either = Either.left("Hello");
        either.apply(s -> Assert.assertEquals("Hello", s), i -> Assert.fail());
        Assert.assertEquals("Testy", either.map(s -> "Testy", integer -> 2));
        either.mapLeft(s -> null)
                .apply(Assert::assertNull, i -> Assert.fail());
        either.mapRight(integer -> 2)
                .apply(s -> Assert.assertEquals("Hello", s), i -> Assert.fail());
    }
}

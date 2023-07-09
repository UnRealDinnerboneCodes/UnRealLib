import com.unrealdinnerbone.unreallib.either.Either;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EitherTest
{
    @Test
    public void test() {
        Either<String, Integer> either = Either.left("Hello");
        either.apply(s -> Assertions.assertEquals("Hello", s), i -> Assertions.fail());
        Assertions.assertEquals("Testy", either.map(s -> "Testy", integer -> 2));
        either.mapLeft(s -> null)
                .apply(Assertions::assertNull, i -> Assertions.fail());
        either.mapRight(integer -> 2)
                .apply(s -> Assertions.assertEquals("Hello", s), i -> Assertions.fail());
    }
}

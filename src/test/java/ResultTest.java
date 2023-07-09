import com.unrealdinnerbone.unreallib.apiutils.IResult;
import com.unrealdinnerbone.unreallib.exception.WebResultException;
import com.unrealdinnerbone.unreallib.json.exception.JsonParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

public class ResultTest {

    @Test
    public void onTest() throws WebResultException, JsonParseException {
        IResult<String> testResult = new IResult<>() {
            @Override
            public String getNow() {
                return "Test";
            }

            @Override
            public CompletableFuture<String> get() {
                return CompletableFuture.completedFuture("Test");
            }
        };

        IResult<String> testResult2 = testResult.map(s -> "2");
        Assertions.assertEquals("2", testResult2.getNow());
        testResult2 = testResult2.map(s -> {
            throw new JsonParseException("Test");
        });
        Assertions.assertThrows(JsonParseException.class, testResult2::getNow);
        CompletableFuture<String> stringCompletableFuture = testResult2.get();
        stringCompletableFuture.whenComplete((s1, throwable) -> Assertions.assertThrows(JsonParseException.class, () -> {
            throw throwable;
        }));


    }

}

//import com.unrealdinnerbone.unreallib.event.EventManager;
//import org.junit.jupiter.api.Assertions;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//
//import java.util.UUID;
//import java.util.concurrent.atomic.AtomicBoolean;
//
//public class EventTest
//{
//
//    @Test
//    public void testEvent() {
//        EventManager<Cheese> voidEventManager = new EventManager<>();
//        UUID uuid = UUID.randomUUID();
//        AtomicBoolean bool = new AtomicBoolean(false);
//        voidEventManager.registerHandler(TestEvent.class, test -> {
//            bool.set(true);
//            Assertions.assertEquals(uuid.toString(), test.cake());
//        });
//        voidEventManager.post(new TestEvent(uuid.toString() + "asd"));
//        Assertions.assertTrue(bool.get());
//    }
//
//    public static record TestEvent(String cake) implements Cheese  {}
//
//    public static interface Cheese {}
//}

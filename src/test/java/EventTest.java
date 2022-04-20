//import com.unrealdinnerbone.unreallib.event.EventManager;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
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
//            Assert.assertEquals(uuid.toString(), test.cake());
//        });
//        voidEventManager.post(new TestEvent(uuid.toString() + "asd"));
//        Assert.assertTrue(bool.get());
//    }
//
//    public static record TestEvent(String cake) implements Cheese  {}
//
//    public static interface Cheese {}
//}

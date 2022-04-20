import com.unrealdinnerbone.unreallib.ActiveList;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ListTest {

    @Test
    public void listTest() {
        List<String> list = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");
        ActiveList<String> activeList = new ActiveList<>(list);

        Assert.assertEquals(list.get(1), activeList.next());
        Assert.assertEquals(list.get(2), activeList.next());
        Assert.assertEquals(list.get(3), activeList.next());
        Assert.assertEquals(list.get(2), activeList.previous());
        Assert.assertEquals(list.get(1), activeList.previous());
        Assert.assertEquals(list.get(0), activeList.previous());
        Assert.assertEquals(list.get(25), activeList.previous());

    }
}

import com.unrealdinnerbone.unreallib.json.JsonUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TestMe
{
    public static void main(String[] args) throws IOException {
        Map<String, AtomicInteger> countMap = new HashMap<>();
        String[] parse = JsonUtil.DEFAULT.parse(String[].class, Files.readString(Path.of("count.json")));
        System.out.println(parse.length);
        for (String s : parse) {
            countMap.computeIfAbsent(s, s1 -> new AtomicInteger()).incrementAndGet();
        }
        countMap.entrySet().stream().sorted((o1, o2) -> o2.getValue().get() - o1.getValue().get()).forEach(stringAtomicIntegerEntry -> {
            System.out.println("- " + stringAtomicIntegerEntry.getKey() + " " + stringAtomicIntegerEntry.getValue().get());
        });

    }
}

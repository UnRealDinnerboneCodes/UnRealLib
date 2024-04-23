import com.unrealdinnerbone.unreallib.LogHelper;
import com.unrealdinnerbone.unreallib.web.HttpHelper;
import org.slf4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

public class Test {

    private static final Logger LOGGER = LogHelper.getLogger();
    public static void main(String[] args) throws IOException, InterruptedException {
        String body = HttpHelper.get(URI.create("https://launchercontent.mojang.com/javaPatchNotes.json")).body();
        Files.write(Path.of("javaPatchNotes.json"), body.getBytes());

    }

    public static record Video(String duration, String title, String upload_date, String url) {}
}

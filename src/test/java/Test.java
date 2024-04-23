import com.unrealdinnerbone.unreallib.LogHelper;
import com.unrealdinnerbone.unreallib.json.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Test {

    private static final Logger LOGGER = LogHelper.getLogger();
    public static void main(String[] args) throws IOException {
        for (Video video : JsonUtil.DEFAULT.parse(Video[].class, Files.readString(Path.of("DireVideo.json")))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM d yyyy", Locale.ENGLISH);

            // Parse the date string to a LocalDate
            LocalDate localDate = LocalDate.parse(video.upload_date, formatter);

            // Convert LocalDate to Instant
            Instant instant = localDate.atStartOfDay().toInstant(ZoneOffset.UTC);
            LOGGER.info("{}", instant);
        }

    }

    public static record Video(String duration, String title, String upload_date, String url) {}
}

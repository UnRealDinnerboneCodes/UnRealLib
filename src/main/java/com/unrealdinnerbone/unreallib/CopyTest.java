package com.unrealdinnerbone.unreallib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class CopyTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CopyTest.class);
    public static void main(String[] args) throws IOException {
        Path path = Path.of("");
        Files.writeString(path.resolve("all.csv"), "user_id,payout_entity_id,ad_share_gross,sub_share_gross,bits_share_gross,bits_developer_share_gross,bits_extension_share_gross,prime_sub_share_gross,bit_share_ad_gross,fuel_rev_gross,bb_rev_gross,report_date,experimental_rev_gross\n");
        Files.list(path.resolve("stuff")).forEach(path1 -> {
            if (!Files.isDirectory(path1) && !path1.getFileName().toString().equalsIgnoreCase("all.csv") && path1.getFileName().toString().endsWith(".csv")) {
                try {
                    LOGGER.info("{}", path1.getFileName());
                    List<String> s = Files.readAllLines(path1);
                    s.remove((int) 0);
                    Files.write(path.resolve("all.csv"), s, Charset.defaultCharset(), StandardOpenOption.APPEND);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

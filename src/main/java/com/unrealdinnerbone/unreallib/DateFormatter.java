package com.unrealdinnerbone.unreallib;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class DateFormatter {

    private static final DateFormat LOGGER_FORMAT = new SimpleDateFormat("LLLL dd YYYY KK:mm:ss a");
    private static final DateFormat fileDateFormat = new SimpleDateFormat("YYYY-LLLL-dd");

    public static String getLoggerDate() {
        return formatData(LOGGER_FORMAT, System.currentTimeMillis());
    }

    public static String formatData(DateFormat format, Long lo) {
        return StringUtils.replace("[{0}]", format.format(new Date(lo)));
    }

    public static String getFileDateFormat() {
        return fileDateFormat.format(new Date());
    }

    public static long parseData(DateFormat dataFormat, String date) {
        try {
            return dataFormat.parse(date).getTime();
        } catch (ParseException e) {
            log.error("There was and error while trying to parse the date", e);
        }
        return -1;
    }
}

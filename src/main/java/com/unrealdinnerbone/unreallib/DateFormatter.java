package com.unrealdinnerbone.unreallib;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter
{

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
            e.printStackTrace();
        }
        return -1;
    }
}

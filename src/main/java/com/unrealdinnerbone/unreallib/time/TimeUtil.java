package com.unrealdinnerbone.unreallib.time;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Slf4j
public class TimeUtil {

    private final static Date DATE = new Date(0);

    public static long formatTime(String time, DateFormat... dateFormats) {
        return Arrays.stream(dateFormats).map(dateFormat -> parseTime(time, dateFormat)).filter(Objects::nonNull).findFirst().orElse(DATE).getTime();
    }

    public static Date parseTime(String time, DateFormat dateFormat) {
        try {
            return dateFormat.parse(time);
        } catch (ParseException ignored) {

        }
        return null;
    }
}

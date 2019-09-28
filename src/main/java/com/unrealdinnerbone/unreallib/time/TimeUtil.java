package com.unrealdinnerbone.unreallib.time;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

@Slf4j
public class TimeUtil {

    public static long formatTime(String time, DateFormat... dateFormats) {
        for (DateFormat dateFormat : dateFormats) {
            return Objects.requireNonNull(parseTime(time, dateFormat)).getTime();
        }
        log.error("There was and error parsing date" + time);
        return -1;
    }

    public static Date parseTime(String time, DateFormat dateFormat) {
        try {
            return dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

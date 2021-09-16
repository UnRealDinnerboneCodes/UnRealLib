package com.unrealdinnerbone.unreallib;

import java.text.DateFormat;
import java.text.ParseException;

public class DateFormatter {

    public static long parseData(DateFormat dataFormat, String date) throws ParseException {
        return dataFormat.parse(date).getTime();
    }
}

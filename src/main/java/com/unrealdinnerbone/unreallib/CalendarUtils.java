package com.unrealdinnerbone.unreallib;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Calendar;

public class CalendarUtils {

    private static final Calendar calendar = Calendar.getInstance();


    public static Today getToday() {
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        return new Today(dayOfMonth, month, year);
    }


    @AllArgsConstructor
    @Getter
    public static class Today {
        private final int day;
        private final int month;
        private final int year;

        @Override
        public String toString() {
            return month + "/" + day + "/" + year;
        }
    }
}

package org.example.qa.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DataGenerator {

    public static String nextMondayDateAsString() {
        LocalDate monday = LocalDate.now();

        while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
            monday = monday.plusDays(1);
        }
        return monday.toString();
    }

}

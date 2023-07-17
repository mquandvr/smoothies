package com.formos.smoothie.utils;

import java.time.LocalDate;

public class DateTimeUtil {

    public static boolean compareDate(LocalDate date1, LocalDate date2) {
        return date1.equals(date2);
    }
}

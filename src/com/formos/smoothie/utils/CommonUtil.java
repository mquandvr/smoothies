package com.formos.smoothie.utils;

import java.text.DecimalFormat;
import java.util.Collection;

public class CommonUtil {

    private static DecimalFormat decimalFormat = new DecimalFormat("#,### VND");

    public static boolean isCollectionNotEmpty(Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }

    public static String convertMoney(long value) {
        return decimalFormat.format(value);
    }
}

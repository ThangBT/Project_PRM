package com.example.buith.project_prm.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class StringUtils {
    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static String formatMoney(Long price){
        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(price);
    }
}

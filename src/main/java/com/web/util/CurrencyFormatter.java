package com.web.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CurrencyFormatter {
    public static String format(String value) {
        Locale locale = new Locale("vi", "VN");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);

        BigDecimal bi = new BigDecimal(value);
        String c = numberFormat.format(bi);

        // System.out.println(String.format("value = %s - c = %s",value,
        // c.replaceAll("[₫]","")));

        return c.replaceAll("[₫]", "VND");
    }

    public static String formateNonPrefix(String value) {

        String c = format(value);

        return c.replaceAll("[VND]", "");
    }
}

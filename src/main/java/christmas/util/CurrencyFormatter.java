package christmas.util;

import java.text.NumberFormat;
import java.util.Locale;

public final class CurrencyFormatter {

    private CurrencyFormatter() {
    }

    public static String formatCurrencyWithNewLine(int amount) {
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.KOREA);
        return formatter.format(amount) + "원\n";
    }

    public static String formatCurrency(int amount) {
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.KOREA);
        return formatter.format(amount) + "원";
    }
}

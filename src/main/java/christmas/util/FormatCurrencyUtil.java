package christmas.util;

import java.text.NumberFormat;
import java.util.Locale;

public final class FormatCurrencyUtil {

    private FormatCurrencyUtil() {
    }

    public static String formatCurrency(int amount) {
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.KOREA);
        return formatter.format(amount) + "원\n";
    }
}

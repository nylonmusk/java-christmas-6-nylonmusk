package christmas.util;

import christmas.constant.complimentary.ComplimentaryItem;

public final class ComplimentaryItemFormatter {

    private ComplimentaryItemFormatter() {
    }

    public static String format(ComplimentaryItem item) {
        return item.getName() + " " + item.getQuantity() + "개\n";
    }
}
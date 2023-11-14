package christmas.util;

import java.util.Map;

public final class OrderFormatter {
    
    private OrderFormatter() {
    }

    public static String format(Map<String, Integer> orderedItems) {
        StringBuilder orderBuilder = new StringBuilder();

        orderedItems.forEach((food, quantity) ->
                orderBuilder.append(food).append(" ").append(quantity).append("개\n"));

        return orderBuilder.toString();
    }
}
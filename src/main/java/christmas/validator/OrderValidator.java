package christmas.validator;

import christmas.constant.menu.Appetizer;
import christmas.constant.menu.Beverage;
import christmas.constant.menu.Dessert;
import christmas.constant.menu.MainCourse;
import christmas.constant.menu.MenuItem;
import christmas.constant.message.ErrorMessage;
import christmas.constant.order.OrderLimit;
import christmas.constant.order.OrderRegex;

import christmas.constant.regex.NumberRegex;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public final class OrderValidator {
    public void validate(Map<String, Integer> orderedItems) {
        if (!areAllItemsValidMenuItems(orderedItems)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
        if (!areQuantitiesValid(orderedItems)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }

        if (areAllItemsBeverage(orderedItems)) {
            throw new IllegalArgumentException(ErrorMessage.BEVERAGE_ONLY_ORDER.getMessage());
        }

        if (validateMaximumItems(orderedItems)) {
            throw new IllegalArgumentException(ErrorMessage.MAXIMUM_ITEMS_EXCEEDED.getMessage());
        }
    }

    public void validateOrderFormat(String orderWithComma) {
        Set<String> uniqueItems = new HashSet<>();
        String[] orderedItems = orderWithComma.split(OrderRegex.COMMA_SPACE.getPattern());
        for (String item : orderedItems) {
            String[] parts = item.split(OrderRegex.HYPHEN.getPattern());

            if (parts.length != 2) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_FORMAT.getMessage());
            }

            if (parts[0].trim().isEmpty() || !parts[1].trim().matches(NumberRegex.DIGITS.getPattern())) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
            }

            if (!uniqueItems.add(parts[0].trim())) {
                throw new IllegalArgumentException(ErrorMessage.DUPLICATE_MENU_ITEM.getMessage());
            }
        }
    }

    public boolean areAllItemsValidMenuItems(Map<String, Integer> orderedItems) {
        return orderedItems.keySet().stream()
                .allMatch(this::isMenuItem);
    }

    private boolean isMenuItem(String itemName) {
        return isItemInMenu(itemName, Appetizer.values()) ||
                isItemInMenu(itemName, Beverage.values()) ||
                isItemInMenu(itemName, Dessert.values()) ||
                isItemInMenu(itemName, MainCourse.values());
    }

    private boolean isItemInMenu(String itemName, MenuItem[] menuItems) {
        return Stream.of(menuItems)
                .anyMatch(menuItem -> menuItem.getName().equals(itemName));
    }

    public boolean validateMaximumItems(Map<String, Integer> orderedItems) {
        int totalQuantity = orderedItems.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        return totalQuantity > OrderLimit.MAX_ITEMS.getValue();
    }

    private boolean areQuantitiesValid(Map<String, Integer> orderedItems) {
        return orderedItems.values().stream()
                .allMatch(quantity -> quantity >= OrderLimit.MIN_QUANTITY.getValue());
    }

    private boolean areAllItemsBeverage(Map<String, Integer> orderedItems) {
        return orderedItems.keySet().stream()
                .allMatch(this::isItemValid);
    }

    private boolean isItemValid(String itemName) {
        return Arrays.stream(Beverage.values())
                .anyMatch(beverage -> beverage.getName().equals(itemName));
    }
}

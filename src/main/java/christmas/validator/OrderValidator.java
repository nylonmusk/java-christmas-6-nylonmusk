package christmas.validator;

import christmas.constant.menu.Appetizer;
import christmas.constant.menu.Beverage;
import christmas.constant.menu.Dessert;
import christmas.constant.menu.MainCourse;
import christmas.constant.menu.MenuItem;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public final class OrderValidator {
    public void validate(Map<String, Integer> orderedItems) {
        if (!areAllItemsValidMenuItems(orderedItems)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        if (!areQuantitiesValid(orderedItems)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }

        if (areAllItemsBeverage(orderedItems)) {
            throw new IllegalArgumentException("[ERROR] 음료만 주문 시, 주문할 수 없습니다. 다시 입력해 주세요.");
        }

        if (validateMaximumItems(orderedItems)) {
            throw new IllegalArgumentException("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.");
        }
    }

    public void validateOrderFormat(String orderWithComma) {
        Set<String> uniqueItems = new HashSet<>();
        String[] orderedItems = orderWithComma.split(",\\s*");
        for (String item : orderedItems) {
            String[] parts = item.split("-");

            if (parts.length != 2) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문 형식입니다. 다시 입력해 주세요.");
            }

            if (parts[0].trim().isEmpty() || !parts[1].trim().matches("\\d+")) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }

            if (!uniqueItems.add(parts[0].trim())) {
                throw new IllegalArgumentException("[ERROR] 중복된 메뉴 항목이 있습니다. 다시 입력해 주세요.");
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
        return totalQuantity > 20;
    }

    private boolean areQuantitiesValid(Map<String, Integer> orderedItems) {
        return orderedItems.values().stream()
                .allMatch(quantity -> quantity >= 1);
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

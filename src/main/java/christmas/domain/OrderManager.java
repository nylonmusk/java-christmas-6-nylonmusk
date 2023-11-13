package christmas.domain;

import christmas.constant.menu.Appetizer;
import christmas.constant.menu.Beverage;
import christmas.constant.menu.Dessert;
import christmas.constant.menu.MainCourse;
import christmas.constant.menu.MenuItem;
import christmas.constant.order.OrderRegex;
import christmas.util.OrderFormatter;
import christmas.validator.OrderValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderManager {
    private final Map<String, Integer> orderedItems = new HashMap<>();
    OrderValidator orderValidator = new OrderValidator();

    public OrderManager() {
    }

    public int calculateTotalPriceBeforeDiscount() {
        return orderedItems.entrySet().stream()
                .mapToInt(entry -> entry.getValue() * findPriceByName(entry.getKey()))
                .sum();
    }

    public int countDessertQuantity() {
        return countItemsByType(Dessert.values());
    }

    public int countMainCourseQuantity() {
        return countItemsByType(MainCourse.values());
    }

    private int countItemsByType(MenuItem[] menuItems) {
        return Arrays.stream(menuItems)
                .mapToInt(item -> orderedItems.getOrDefault(item.getName(), 0))
                .sum();
    }

    private int findPriceByName(String itemName) {
        return getAllMenuItems().stream()
                .filter(menuItem -> menuItem.getName().equals(itemName))
                .findFirst()
                .map(MenuItem::getPrice)
                .orElse(0);
    }

    private List<MenuItem> getAllMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.addAll(Arrays.asList(Dessert.values()));
        menuItems.addAll(Arrays.asList(Beverage.values()));
        menuItems.addAll(Arrays.asList(Appetizer.values()));
        menuItems.addAll(Arrays.asList(MainCourse.values()));
        return menuItems;
    }

    public String formatOrderList(String orderWithComma) {
        processOrders(orderWithComma);

        orderValidator.validate(orderedItems);

        return OrderFormatter.format(orderedItems);
    }

    private void processOrders(String orders) {
        String[] orderArray = orders.split(OrderRegex.COMMA_SPACE.getPattern());
        for (String order : orderArray) {
            String[] orderParts = order.trim().split(OrderRegex.HYPHEN.getPattern());

            String item = orderParts[0].trim();
            int quantity = Integer.parseInt(orderParts[1].trim());
            orderedItems.merge(item, quantity, Integer::sum);
        }
    }

}

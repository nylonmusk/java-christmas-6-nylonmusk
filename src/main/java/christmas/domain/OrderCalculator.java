package christmas.domain;

import christmas.constant.menu.Dessert;
import christmas.constant.menu.MainCourse;
import christmas.constant.menu.MenuItem;

import java.util.Arrays;

public class OrderCalculator {

    private final MenuItemRepository menuItemRepository;

    public OrderCalculator(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public int calculateTotalPriceBeforeDiscount(OrderedItems orderedItems) {
        return orderedItems.getItemsMap().entrySet().stream()
                .mapToInt(entry -> entry.getValue() * menuItemRepository.findPriceByName(entry.getKey()))
                .sum();
    }

    public int countDessertQuantity(OrderedItems orderedItems) {
        return countItemsByType(Dessert.values(), orderedItems);
    }

    public int countMainCourseQuantity(OrderedItems orderedItems) {
        return countItemsByType(MainCourse.values(), orderedItems);
    }

    private int countItemsByType(MenuItem[] menuItems, OrderedItems orderedItems) {
        return Arrays.stream(menuItems)
                .mapToInt(item -> orderedItems.getItemsMap().getOrDefault(item.getName(), 0))
                .sum();
    }

}

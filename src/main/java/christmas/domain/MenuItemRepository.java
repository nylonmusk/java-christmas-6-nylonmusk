package christmas.domain;

import christmas.constant.menu.Appetizer;
import christmas.constant.menu.Beverage;
import christmas.constant.menu.Dessert;
import christmas.constant.menu.MainCourse;
import christmas.constant.menu.MenuItem;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MenuItemRepository {

    public int findPriceByName(String itemName) {
        return getAllMenuItems().stream()
                .filter(menuItem -> menuItem.getName().equals(itemName))
                .findFirst()
                .map(MenuItem::getPrice)
                .orElse(0);
    }

    private List<MenuItem> getAllMenuItems() {
        return Stream.of(Dessert.values(), Beverage.values(), Appetizer.values(), MainCourse.values())
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }
}

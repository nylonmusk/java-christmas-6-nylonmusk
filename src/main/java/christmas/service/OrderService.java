package christmas.service;

import christmas.constant.order.OrderRegex;
import christmas.domain.OrderedItems;
import christmas.util.OrderFormatter;
import christmas.validator.OrderValidator;

public class OrderService {
    private final OrderValidator orderValidator;

    public OrderService(OrderValidator orderValidator) {
        this.orderValidator = orderValidator;
    }

    public OrderedItems getOrderedItems(String orderWithComma) {
        OrderedItems orderedItems = parseAndAddItems(orderWithComma);
        validateOrders(orderedItems);
        return orderedItems;
    }

    public String getOrderList(OrderedItems orderedItems) {
        return OrderFormatter.format(orderedItems.getItemsMap());
    }

    private void validateOrders(OrderedItems orderedItems) {
        orderValidator.validate(orderedItems.getItemsMap());
    }

    private OrderedItems parseAndAddItems(String orders) {
        OrderedItems orderedItems = new OrderedItems();
        String[] orderArray = orders.split(OrderRegex.COMMA_SPACE.getPattern());
        for (String order : orderArray) {
            addParsedItemToOrderedItems(orderedItems, parseOrder(order));
        }
        return orderedItems;
    }

    private void addParsedItemToOrderedItems(OrderedItems orderedItems, String[] parsedOrder) {
        String item = parsedOrder[0];
        int quantity = Integer.parseInt(parsedOrder[1]);
        orderedItems.addItem(item, quantity);
    }

    private String[] parseOrder(String order) {
        return order.trim().split(OrderRegex.HYPHEN.getPattern());
    }

}


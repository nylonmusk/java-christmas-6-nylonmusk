package christmas.controller;

import christmas.domain.MenuItemRepository;
import christmas.domain.OrderCalculator;
import christmas.domain.OrderedItems;
import christmas.service.OrderService;
import christmas.service.DiscountService;
import christmas.domain.BadgeManager;
import christmas.domain.ComplimentaryManager;
import christmas.validator.OrderValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Controller {
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();
    private final OrderService orderService;
    private final DiscountService discountService = new DiscountService();
    private final ComplimentaryManager complimentaryManager = new ComplimentaryManager();
    private final BadgeManager badgeManager = new BadgeManager();
    private final OrderCalculator orderCalculator;
    private final MenuItemRepository menuItemRepository = new MenuItemRepository();

    public Controller() {
        OrderValidator orderValidator = new OrderValidator();
        this.orderCalculator = new OrderCalculator(menuItemRepository);
        this.orderService = new OrderService(orderValidator);
    }

    public void run() {
        outputView.printWelcomeMessage();
        int day = inputView.readDay();

        OrderedItems orderedItems = getOrderedItems(day);
        processOrder(orderedItems);
        printDiscountInfo(day, orderedItems);
        printFinalOrderResults(day, orderedItems);
    }

    private OrderedItems getOrderedItems(int day) {
        OrderedItems orderedItems;
        while (true) {
            try {
                String orderWithComma = inputView.readOrderMenu();
                orderedItems = orderService.getOrderedItems(orderWithComma);
                outputView.printPreviewOfEventBenefits(day);
                break;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
        return orderedItems;
    }

    private void processOrder(OrderedItems orderedItems) {
        outputView.printOrder(orderService.getOrderList(orderedItems));

        int totalPriceBeforeDiscount = orderCalculator.calculateTotalPriceBeforeDiscount(orderedItems);
        outputView.printTotalOrderAmountBeforeDiscount(totalPriceBeforeDiscount);

        String complimentaryItems = complimentaryManager.getComplimentary(totalPriceBeforeDiscount);
        outputView.printComplimentaryItems(complimentaryItems);
    }

    private void printDiscountInfo(int day, OrderedItems orderedItems) {
        int dessertQuantity = orderCalculator.countDessertQuantity(orderedItems);
        int mainCourseQuantity = orderCalculator.countMainCourseQuantity(orderedItems);

        String discountInformation = discountService.describeDiscounts(day, dessertQuantity, mainCourseQuantity,
                orderCalculator.calculateTotalPriceBeforeDiscount(orderedItems));
        outputView.printDetailBenefits(discountInformation);
    }

    private void printFinalOrderResults(int day, OrderedItems orderedItems) {
        int totalDiscounts = discountService.calculateTotalDiscounts(
                day,
                orderCalculator.countDessertQuantity(orderedItems),
                orderCalculator.countMainCourseQuantity(orderedItems),
                orderCalculator.calculateTotalPriceBeforeDiscount(orderedItems)
        );

        outputView.printTotalDiscounts(totalDiscounts);

        int finalPrice = discountService.calculateFinalPrice(
                orderCalculator.calculateTotalPriceBeforeDiscount(orderedItems)
                , totalDiscounts
        );

        outputView.printFinalOrderAmount(finalPrice);

        String badge = badgeManager.getBadge(totalDiscounts);
        outputView.printBadge(badge);

        outputView.printEventNotice(orderCalculator.calculateTotalPriceBeforeDiscount(orderedItems));
    }
}

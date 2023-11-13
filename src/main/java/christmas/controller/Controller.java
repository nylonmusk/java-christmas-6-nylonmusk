package christmas.controller;

import christmas.domain.BadgeManager;
import christmas.domain.ComplimentaryManager;
import christmas.domain.OrderManager;
import christmas.service.DiscountService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Controller {
    OutputView outputView = new OutputView();
    InputView inputView = new InputView();
    OrderManager orderManager = new OrderManager();
    DiscountService discountService = new DiscountService();
    ComplimentaryManager complimentaryManager = new ComplimentaryManager();
    BadgeManager badgeManager = new BadgeManager();

    public Controller() {

    }

    public void run() {
        outputView.printWelcomeMessage();
        int day = inputView.readDay();

        String orderWithComma = inputView.readOrderMenu();

        outputView.printPreviewOfEventBenefits();
        String formattedOrderList = orderManager.formatOrderList(orderWithComma);


        int dessertQuantity = orderManager.countDessertQuantity();
        int mainCourseQuantity = orderManager.countMainCourseQuantity();

        int totalPriceBeforeDiscount = orderManager.calculateTotalPriceBeforeDiscount();

        outputView.printOrder(formattedOrderList);

        outputView.printTotalOrderAmountBeforeDiscount(totalPriceBeforeDiscount);
        String complimentaryItems = complimentaryManager.getComplimentary(totalPriceBeforeDiscount);
        outputView.printComplimentaryItems(complimentaryItems);

        String discountInformation = discountService.describeDiscounts(day, dessertQuantity, mainCourseQuantity,
                totalPriceBeforeDiscount);
        outputView.printDetailBenefits(discountInformation);

        int totalDiscounts = discountService.calculateTotalDiscounts(day, dessertQuantity, mainCourseQuantity,
                totalPriceBeforeDiscount);
        outputView.printTotalDiscounts(totalDiscounts);

        int finalPrice = discountService.calculateFinalPrice(totalPriceBeforeDiscount,
                discountService.getDiscountWithoutComplimentary(day, dessertQuantity, mainCourseQuantity));
        outputView.printFinalOrderAmount(finalPrice);


        String badge = badgeManager.getBadge(totalDiscounts);
        outputView.printBadge(badge);

        outputView.printEventNotice(totalPriceBeforeDiscount);

    }
}

package christmas.controller;

import christmas.domain.BadgeManager;
import christmas.domain.ComplimentaryManager;
import christmas.domain.DiscountManager;
import christmas.domain.OrderManager;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Controller {
    OutputView outputView = new OutputView();
    InputView inputView = new InputView();
    OrderManager orderManager = new OrderManager();
    DiscountManager discountManager = new DiscountManager();
    ComplimentaryManager complimentaryManager = new ComplimentaryManager();

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
        // 1. 총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.
        // 2. 음료만 주문 시, 주문할 수 없습니다.

        String discountInformation = discountManager.describeDiscounts(day, dessertQuantity, mainCourseQuantity,
                totalPriceBeforeDiscount);
        outputView.printDetailBenefits(discountInformation);

        int totalDiscounts = discountManager.calculateTotalDiscounts(day, dessertQuantity, mainCourseQuantity,
                totalPriceBeforeDiscount);
        outputView.printTotalDiscounts(totalDiscounts);

        int finalPrice = discountManager.calculateFinalPrice(totalPriceBeforeDiscount,
                totalDiscounts - discountManager.getComplimentaryDiscount(totalPriceBeforeDiscount));
        outputView.printFinalOrderAmount(finalPrice);

        BadgeManager badgeManager = new BadgeManager();
        String badge = badgeManager.getBadge(totalDiscounts);
        outputView.printBadge(badge);

        if (totalPriceBeforeDiscount < 10000) {
            System.out.println("\n<이벤트 주의 사항>");
            System.out.println("총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.");
        }

    }
}

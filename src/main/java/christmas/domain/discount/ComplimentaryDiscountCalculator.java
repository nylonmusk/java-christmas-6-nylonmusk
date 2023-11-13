package christmas.domain.discount;

import christmas.constant.complimentary.ComplimentaryItem;
import christmas.constant.menu.Beverage;

public class ComplimentaryDiscountCalculator {
    public int calculate(int totalPriceBeforeDiscount) {
        int complimentaryDiscount = 0;

        for (ComplimentaryItem item : ComplimentaryItem.values()) {
            if (totalPriceBeforeDiscount >= item.getQualifyingAmount()) {
                int beveragePrice = Beverage.getPrice(item.getName());

                complimentaryDiscount += beveragePrice * item.getQuantity();
            }
        }

        return complimentaryDiscount;
    }
}

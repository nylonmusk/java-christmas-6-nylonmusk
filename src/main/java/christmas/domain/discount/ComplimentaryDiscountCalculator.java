package christmas.domain.discount;

import christmas.constant.complimentary.ComplimentaryItem;
import christmas.constant.discount.DiscountValue;
import christmas.constant.menu.Beverage;

public class ComplimentaryDiscountCalculator {

    public int calculate(int totalPriceBeforeDiscount) {
        int complimentaryDiscount = DiscountValue.NO_DISCOUNT.getValue();

        for (ComplimentaryItem item : ComplimentaryItem.values()) {
            if (totalPriceBeforeDiscount >= item.getQualifyingAmount()) {
                complimentaryDiscount += Beverage.getPrice(item.getName()) * item.getQuantity();
            }
        }

        return complimentaryDiscount;
    }

}

package christmas.domain.discount;

import christmas.constant.discount.DiscountValue;

public class ChristmasDiscountCalculator {

    public int calculate(int day) {
        if (day <= 25) {
            return DiscountValue.START_DISCOUNT.getValue() + (day - 1) * DiscountValue.DAILY_INCREMENT.getValue();
        }
        return 0;
    }
}


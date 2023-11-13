package christmas.domain.discount;

import christmas.constant.discount.DiscountValue;

public class ChristmasDiscountCalculator {

    public int calculate(int day) {
        if (day <= DiscountValue.CHRISTMAS_EVENT_DEADLINE.getValue()) {
            return DiscountValue.START_CHRISTMAS_DISCOUNT.getValue() + (day - 1) * DiscountValue.CHRISTMAS_DISCOUNT_DAILY_INCREMENT.getValue();
        }
        return DiscountValue.NO_DISCOUNT.getValue();
    }
}


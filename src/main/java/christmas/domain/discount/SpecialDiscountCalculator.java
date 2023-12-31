package christmas.domain.discount;

import christmas.constant.calendar.SpecialDay;
import christmas.constant.discount.DiscountValue;

public class SpecialDiscountCalculator {

    public int calculate(int day) {
        if (SpecialDay.CHRISTMAS.isTodaySpecial(day)) {
            return DiscountValue.SPECIAL_DISCOUNT.getValue();
        }
        return DiscountValue.NO_DISCOUNT.getValue();
    }
}


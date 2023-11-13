package christmas.domain.discount;

import christmas.constant.discount.DiscountValue;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekdayDiscountCalculator {

    public int calculate(int quantity, int day) {
        if (!isWeekend(day)) {
            return quantity * DiscountValue.WEEKDAY_DISCOUNT_PER_ITEM.getValue();
        }
        return 0;
    }

    private boolean isWeekend(int day) {
        LocalDate date = LocalDate.of(2023, 12, day);
        return date.getDayOfWeek() == DayOfWeek.FRIDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY;
    }
}

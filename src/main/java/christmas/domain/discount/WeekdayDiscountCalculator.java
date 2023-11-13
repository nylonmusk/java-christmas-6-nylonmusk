package christmas.domain.discount;

import christmas.constant.calendar.CurrentYearMonth;
import christmas.constant.discount.DiscountValue;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekdayDiscountCalculator {

    public int calculate(int quantity, int day) {
        if (!isWeekend(day)) {
            return quantity * DiscountValue.WEEKDAY_DISCOUNT_PER_ITEM.getValue();
        }
        return DiscountValue.NO_DISCOUNT.getValue();
    }

    private boolean isWeekend(int day) {
        LocalDate today = LocalDate.of(CurrentYearMonth.YEAR.getDate(), CurrentYearMonth.MONTH.getDate(), day);
        return today.getDayOfWeek() == DayOfWeek.FRIDAY || today.getDayOfWeek() == DayOfWeek.SATURDAY;
    }
}

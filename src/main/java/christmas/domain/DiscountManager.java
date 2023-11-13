package christmas.domain;

import christmas.constant.SpecialDay;
import christmas.constant.complimentary.ComplimentaryItem;
import christmas.constant.menu.Beverage;
import christmas.util.FormatCurrencyUtil;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class DiscountManager {

    private static final int START_DISCOUNT = 1000;
    private static final int DAILY_INCREMENT = 100;
    private static final int WEEKDAY_DISCOUNT_PER_ITEM = 2023;
    private static final int WEEKEND_DISCOUNT_PER_ITEM = 2023;
    private static final int SPECIAL_DISCOUNT = 1000;

    public String describeDiscounts(int day, int dessertQuantity, int mainQuantity, int totalPriceBeforeDiscount) {
        if ( totalPriceBeforeDiscount < 10000) {
            return ("없음\n");
        }

        StringBuilder discountDescription = new StringBuilder();

        discountDescription.append(describeChristmasDdayDiscount(day))
                .append(describeWeekdayDiscount(dessertQuantity, day))
                .append(describeWeekendDiscount(mainQuantity, day))
                .append(describeSpecialDiscount(day))
                .append(describeComplimentaryDiscount(totalPriceBeforeDiscount));

        if (discountDescription.isEmpty()) {
            discountDescription.append("없음\n");
        }
        return discountDescription.toString();
    }

    public int calculateTotalDiscounts(int day, int dessertQuantity, int mainQuantity, int totalPriceBeforeDiscount) {
        if (totalPriceBeforeDiscount < 10000) return 0;
        return getDiscountWithoutComplimentary(day, dessertQuantity, mainQuantity) + getComplimentaryDiscount(
                totalPriceBeforeDiscount);
    }

    public int getDiscountWithoutComplimentary(int day, int dessertQuantity, int mainQuantity) {
        int christmasDiscount = calculateChristmasDdayDiscount(day);
        int weekdayDiscount = calculateWeekdayDiscount(dessertQuantity, day);
        int weekendDiscount = calculateWeekendDiscount(mainQuantity, day);
        int specialDiscount = calculateSpecialDiscount(day);

        return christmasDiscount + weekdayDiscount + weekendDiscount + specialDiscount;
    }

    public int getComplimentaryDiscount(int totalPriceBeforeDiscount) {
        int complimentaryDiscount = 0;

        for (ComplimentaryItem item : ComplimentaryItem.values()) {
            if (totalPriceBeforeDiscount >= item.getQualifyingAmount()) {
                int beveragePrice = Beverage.getPrice(item.getName());

                complimentaryDiscount += beveragePrice * item.getQuantity();
            }
        }

        return complimentaryDiscount;
    }

    public int calculateChristmasDdayDiscount(int day) {
        if (day <= 25) {
            return START_DISCOUNT + (day - 1) * DAILY_INCREMENT;
        }
        return 0;

    }

    public int calculateWeekdayDiscount(int quantity, int dayOfMonth) {
        if (!isDayWeekend(dayOfMonth)) {
            return quantity * WEEKDAY_DISCOUNT_PER_ITEM;
        }
        return 0;
    }

    public int calculateWeekendDiscount(int quantity, int dayOfMonth) {
        if (isDayWeekend(dayOfMonth)) {
            return quantity * WEEKEND_DISCOUNT_PER_ITEM;
        }
        return 0;
    }

    private boolean isDayWeekend(int dayOfMonth) {
        LocalDate date = LocalDate.of(2023, 12, dayOfMonth);
        return date.getDayOfWeek() == DayOfWeek.FRIDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    public int calculateSpecialDiscount(int day) {
        if (SpecialDay.CHRISTMAS.isTodaySpecial(day)) {
            return SPECIAL_DISCOUNT;
        }
        return 0;
    }

    public int calculateFinalPrice(int totalPriceBeforeDiscount, int totalDiscounts) {
        return totalPriceBeforeDiscount - totalDiscounts;
    }

    private String describeChristmasDdayDiscount(int day) {
        int discount = calculateChristmasDdayDiscount(day);
        if (discount > 0) {
            return ("크리스마스 디데이 할인: -" + FormatCurrencyUtil.formatCurrency(discount));
        }
        return "";
    }

    private String describeWeekdayDiscount(int quantity, int day) {
        int discount = calculateWeekdayDiscount(quantity, day);
        if (discount > 0) {
            return ("평일 할인: -" + FormatCurrencyUtil.formatCurrency(discount));
        }
        return "";
    }

    private String describeWeekendDiscount(int quantity, int day) {
        int discount = calculateWeekendDiscount(quantity, day);
        if (discount > 0) {
            return ("주말 할인: -" + FormatCurrencyUtil.formatCurrency(discount));
        }
        return "";
    }

    private String describeSpecialDiscount(int day) {
        int discount = calculateSpecialDiscount(day);
        if (discount > 0) {
            return ("특별 할인: -" + FormatCurrencyUtil.formatCurrency(discount));
        }
        return "";
    }

    private String describeComplimentaryDiscount(int totalPriceBeforeDiscount) {
        int discount = getComplimentaryDiscount(totalPriceBeforeDiscount);
        if (discount > 0) {
            return ("증정 이벤트: -" + FormatCurrencyUtil.formatCurrency(discount));
        }
        return "";
    }
}

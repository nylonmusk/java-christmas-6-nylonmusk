package christmas.service;

import christmas.constant.discount.DiscountDescription;
import christmas.constant.discount.DiscountValue;
import christmas.domain.discount.ChristmasDiscountCalculator;
import christmas.domain.discount.ComplimentaryDiscountCalculator;
import christmas.domain.discount.SpecialDiscountCalculator;
import christmas.domain.discount.WeekdayDiscountCalculator;
import christmas.domain.discount.WeekendDiscountCalculator;
import christmas.util.FormatCurrencyUtil;

public class DiscountService {

    private ChristmasDiscountCalculator christmasDiscountCalculator;
    private WeekdayDiscountCalculator weekdayDiscountCalculator;
    private WeekendDiscountCalculator weekendDiscountCalculator;
    private SpecialDiscountCalculator specialDiscountCalculator;
    private ComplimentaryDiscountCalculator complimentaryDiscountCalculator;

    public DiscountService() {
        this.christmasDiscountCalculator = new ChristmasDiscountCalculator();
        this.weekdayDiscountCalculator = new WeekdayDiscountCalculator();
        this.weekendDiscountCalculator = new WeekendDiscountCalculator();
        this.specialDiscountCalculator = new SpecialDiscountCalculator();
        this.complimentaryDiscountCalculator = new ComplimentaryDiscountCalculator();
    }

    public String describeDiscounts(int day, int dessertQuantity, int mainQuantity, int totalPriceBeforeDiscount) {
        if (totalPriceBeforeDiscount < 10000) {
            return DiscountDescription.NO_DISCOUNT.getDescription();
        }

        StringBuilder discountDescription = new StringBuilder();

        discountDescription.append(describeChristmasDdayDiscount(day))
                .append(describeWeekdayDiscount(dessertQuantity, day))
                .append(describeWeekendDiscount(mainQuantity, day))
                .append(describeSpecialDiscount(day))
                .append(describeComplimentaryDiscount(totalPriceBeforeDiscount));

        if (discountDescription.isEmpty()) {
            discountDescription.append(DiscountDescription.NO_DISCOUNT.getDescription());
        }
        return discountDescription.toString();
    }

    public int calculateTotalDiscounts(int day, int dessertQuantity, int mainQuantity, int totalPriceBeforeDiscount) {
        if (totalPriceBeforeDiscount < DiscountValue.MINIMUM_DISCOUNT_THRESHOLD.getValue()) {
            return DiscountValue.NO_DISCOUNT.getValue();
        }
        int christmasDiscount = christmasDiscountCalculator.calculate(day);
        int weekdayDiscount = weekdayDiscountCalculator.calculate(dessertQuantity, day);
        int weekendDiscount = weekendDiscountCalculator.calculate(mainQuantity, day);
        int specialDiscount = specialDiscountCalculator.calculate(day);
        int complimentaryDiscount = complimentaryDiscountCalculator.calculate(totalPriceBeforeDiscount);

        return christmasDiscount + weekdayDiscount + weekendDiscount + specialDiscount + complimentaryDiscount;
    }

    public int getDiscountWithoutComplimentary(int day, int dessertQuantity, int mainQuantity) {
        int christmasDiscount = christmasDiscountCalculator.calculate(day);
        int weekdayDiscount = weekdayDiscountCalculator.calculate(dessertQuantity, day);
        int weekendDiscount = weekendDiscountCalculator.calculate(mainQuantity, day);
        int specialDiscount = specialDiscountCalculator.calculate(day);

        return christmasDiscount + weekdayDiscount + weekendDiscount + specialDiscount;
    }

    public int calculateFinalPrice(int totalPriceBeforeDiscount, int totalDiscounts) {
        return totalPriceBeforeDiscount - totalDiscounts;
    }

    private String describeChristmasDdayDiscount(int day) {
        int discount = christmasDiscountCalculator.calculate(day);
        if (hasDiscount(discount)) {
            return DiscountDescription.CHRISTMAS_D_DAY_DISCOUNT.getDescription() + FormatCurrencyUtil.formatCurrency(
                    discount);
        }
        return DiscountDescription.EMPTY.getDescription();
    }

    private String describeWeekdayDiscount(int dessertQuantity, int day) {
        int discount = weekdayDiscountCalculator.calculate(dessertQuantity, day);
        if (hasDiscount(discount)) {
            return DiscountDescription.WEEKDAY_DISCOUNT.getDescription() + FormatCurrencyUtil.formatCurrency(discount);
        }
        return DiscountDescription.EMPTY.getDescription();
    }

    private String describeWeekendDiscount(int mainQuantity, int day) {
        int discount = weekendDiscountCalculator.calculate(mainQuantity, day);
        if (hasDiscount(discount)) {
            return DiscountDescription.WEEKEND_DISCOUNT.getDescription() + FormatCurrencyUtil.formatCurrency(discount);
        }
        return DiscountDescription.EMPTY.getDescription();
    }

    private String describeSpecialDiscount(int day) {
        int discount = specialDiscountCalculator.calculate(day);
        if (hasDiscount(discount)) {
            return DiscountDescription.SPECIAL_DISCOUNT.getDescription() + FormatCurrencyUtil.formatCurrency(discount);
        }
        return DiscountDescription.EMPTY.getDescription();
    }

    private String describeComplimentaryDiscount(int totalPriceBeforeDiscount) {
        int discount = complimentaryDiscountCalculator.calculate(totalPriceBeforeDiscount);
        if (hasDiscount(discount)) {
            return DiscountDescription.COMPLIMENTARY_DISCOUNT.getDescription() + FormatCurrencyUtil.formatCurrency(
                    discount);
        }
        return DiscountDescription.EMPTY.getDescription();
    }

    private boolean hasDiscount(int discountAmount) {
        return discountAmount > DiscountValue.NO_DISCOUNT.getValue();
    }

}
package christmas.discount;

import christmas.domain.discount.WeekendDiscountCalculator;
import christmas.constant.discount.DiscountValue;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WeekendDiscountCalculatorTest {

    private WeekendDiscountCalculator discountCalculator = new WeekendDiscountCalculator();

    @Test
    void 주말_할인_계산_테스트() {

        int weekend = 15;
        int quantity = 3;
        int expectedDiscount = quantity * DiscountValue.WEEKEND_DISCOUNT_PER_ITEM.getValue();

        int calculatedDiscount = discountCalculator.calculate(quantity, weekend);
        assertThat(calculatedDiscount).isEqualTo(expectedDiscount);
    }

    @Test
    void 평일_할인_없음_테스트() {

        int weekday = 20;
        int quantity = 3;

        int calculatedDiscount = discountCalculator.calculate(quantity, weekday);
        assertThat(calculatedDiscount).isEqualTo(DiscountValue.NO_DISCOUNT.getValue());
    }
}

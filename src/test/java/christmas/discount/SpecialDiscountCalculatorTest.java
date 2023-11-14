package christmas.discount;

import christmas.domain.discount.SpecialDiscountCalculator;
import christmas.constant.calendar.SpecialDay;
import christmas.constant.discount.DiscountValue;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpecialDiscountCalculatorTest {

    private SpecialDiscountCalculator discountCalculator = new SpecialDiscountCalculator();

    @Test
    void 특별한_날_할인_적용_테스트() {

        int specialDay = SpecialDay.CHRISTMAS.getDay();
        int calculatedDiscount = discountCalculator.calculate(specialDay);

        assertThat(calculatedDiscount).isEqualTo(DiscountValue.SPECIAL_DISCOUNT.getValue());
    }

    @Test
    void 특별하지_않은_날_할인_없음_테스트() {

        int nonSpecialDay = 18;
        int calculatedDiscount = discountCalculator.calculate(nonSpecialDay);

        assertThat(calculatedDiscount).isEqualTo(DiscountValue.NO_DISCOUNT.getValue());
    }
}

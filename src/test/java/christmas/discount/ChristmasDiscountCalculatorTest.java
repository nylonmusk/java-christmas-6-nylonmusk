package christmas.discount;

import christmas.domain.discount.ChristmasDiscountCalculator;
import christmas.constant.discount.DiscountValue;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChristmasDiscountCalculatorTest {

    private ChristmasDiscountCalculator discountCalculator = new ChristmasDiscountCalculator();

    @Test
    void 마감일_이후_할인_없음_테스트() {
        int dayAfterDeadline = DiscountValue.CHRISTMAS_EVENT_DEADLINE.getValue() + 1;
        int discount = discountCalculator.calculate(dayAfterDeadline);
        assertThat(discount).isEqualTo(DiscountValue.NO_DISCOUNT.getValue());
    }

    @Test
    void 디데이_할인_테스트() {
        int dDay = DiscountValue.CHRISTMAS_EVENT_DEADLINE.getValue();
        int expectedDiscount = DiscountValue.START_CHRISTMAS_DISCOUNT.getValue()
                + (dDay - 1) * DiscountValue.CHRISTMAS_DISCOUNT_DAILY_INCREMENT.getValue();
        int discount = discountCalculator.calculate(dDay);
        assertThat(discount).isEqualTo(expectedDiscount);
    }

    @Test
    void 마감일_전_증가하는_할인_테스트() {
        int dayBeforeDeadline = DiscountValue.CHRISTMAS_EVENT_DEADLINE.getValue() - 1;
        int expectedDiscount = DiscountValue.START_CHRISTMAS_DISCOUNT.getValue()
                + (dayBeforeDeadline - 1) * DiscountValue.CHRISTMAS_DISCOUNT_DAILY_INCREMENT.getValue();
        int discount = discountCalculator.calculate(dayBeforeDeadline);
        assertThat(discount).isEqualTo(expectedDiscount);
    }
}

package christmas.complimentary;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.domain.discount.ComplimentaryDiscountCalculator;

import org.junit.jupiter.api.Test;

class ComplimentaryDiscountCalculatorTest {

    private final ComplimentaryDiscountCalculator discountCalculator = new ComplimentaryDiscountCalculator();

    @Test
    void 높은_가격_할인_적용_테스트() {
        int discount = discountCalculator.calculate(120000);
        assertThat(discount).isPositive();
    }

    @Test
    void 낮은_가격_할인_없음_테스트() {
        int discount = discountCalculator.calculate(50000);
        assertThat(discount).isZero();
    }
}
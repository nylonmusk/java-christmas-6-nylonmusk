package christmas.discount;

import christmas.constant.menu.Beverage;
import christmas.domain.discount.ComplimentaryDiscountCalculator;
import christmas.constant.complimentary.ComplimentaryItem;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ComplimentaryDiscountCalculatorTest {

    private ComplimentaryDiscountCalculator discountCalculator = new ComplimentaryDiscountCalculator();

    @Test
    void 사은품_할인_계산_테스트() {
        int qualifyingAmountForComplimentary = ComplimentaryItem.CHAMPAGNE.getQualifyingAmount();
        int expectedDiscount = Beverage.getPrice(ComplimentaryItem.CHAMPAGNE.getName())
                * ComplimentaryItem.CHAMPAGNE.getQuantity();

        int calculatedDiscount = discountCalculator.calculate(qualifyingAmountForComplimentary);
        assertThat(calculatedDiscount).isEqualTo(expectedDiscount);
    }

    @Test
    void 할인_없는_금액_테스트() {
        int belowQualifyingAmount = ComplimentaryItem.CHAMPAGNE.getQualifyingAmount() - 1000;

        int calculatedDiscount = discountCalculator.calculate(belowQualifyingAmount);
        assertThat(calculatedDiscount).isZero();
    }
}

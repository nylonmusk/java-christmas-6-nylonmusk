package christmas.discount;

import christmas.service.DiscountService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountServiceTest {

    private DiscountService discountService;

    @BeforeEach
    void setUp() {
        discountService = new DiscountService();
    }

    @Test
    void 총_할인_금액_계산_테스트() {
        int day = 24;
        int dessertQuantity = 6;
        int mainQuantity = 5;
        int totalPriceBeforeDiscount = 215000;

        int totalDiscounts = discountService.calculateTotalDiscounts(day, dessertQuantity, mainQuantity,
                totalPriceBeforeDiscount);

        int expectedDiscount = 41438;
        assertThat(totalDiscounts).isEqualTo(expectedDiscount);
    }

    @Test
    void 할인_설명_테스트() {
        int day = 24;
        int dessertQuantity = 6;
        int mainQuantity = 5;
        int totalPriceBeforeDiscount = 215000;

        String discountDescription = discountService.describeDiscounts(day, dessertQuantity, mainQuantity,
                totalPriceBeforeDiscount);

        String expectedDescription = """
                크리스마스 디데이 할인: -3,300원
                평일 할인: -12,138원
                특별 할인: -1,000원
                증정 이벤트: -25,000원
                """;
        assertThat(discountDescription).isEqualTo(expectedDescription);
    }

    @Test
    void 최소_할인_적격_금액_미달시_할인_없음_테스트() {
        int totalPriceBeforeDiscount = 3000;
        int totalDiscounts = discountService.calculateTotalDiscounts(3, 1, 1, totalPriceBeforeDiscount);
        assertThat(totalDiscounts).isZero();
    }

    @Test
    void 최종_가격_계산_테스트() {
        int totalPriceBeforeDiscount = 100000;
        int totalDiscounts = 20000;
        int finalPrice = discountService.calculateFinalPrice(totalPriceBeforeDiscount, totalDiscounts);
        assertThat(finalPrice).isEqualTo(80000);
    }
}

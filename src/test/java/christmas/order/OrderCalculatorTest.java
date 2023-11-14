package christmas.order;

import christmas.domain.MenuItemRepository;
import christmas.domain.OrderCalculator;
import christmas.domain.OrderedItems;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderCalculatorTest {

    private OrderCalculator orderCalculator;

    @BeforeEach
    void setUp() {
        MenuItemRepository menuItemRepository = createMockMenuItemRepository();
        orderCalculator = new OrderCalculator(menuItemRepository);
    }

    @Test
    void 주문_합계_계산_테스트() {
        OrderedItems orderedItems = new OrderedItems();
        orderedItems.addItem("크리스마스파스타", 1);  // 25000원
        orderedItems.addItem("티본스테이크", 2);     // 55000원

        int totalPrice = orderCalculator.calculateTotalPriceBeforeDiscount(orderedItems);

        assertThat(totalPrice).isEqualTo(135000); // 25000 + (2 * 55000)
    }

    @Test
    void 메뉴_항목별_수량_계산_테스트() {
        OrderedItems orderedItems = new OrderedItems();
        orderedItems.addItem("크리스마스파스타", 2);
        orderedItems.addItem("초코케이크", 3);

        int pastaQuantity = orderCalculator.countMainCourseQuantity(orderedItems);
        int drinkQuantity = orderCalculator.countDessertQuantity(orderedItems);

        assertThat(pastaQuantity).isEqualTo(2);
        assertThat(drinkQuantity).isEqualTo(3);
    }

    private MenuItemRepository createMockMenuItemRepository() {
        return new MenuItemRepository() {
            @Override
            public int findPriceByName(String name) {
                if ("크리스마스파스타".equals(name)) {
                    return 25000;
                }
                if ("티본스테이크".equals(name)) {
                    return 55000;
                }
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        };
    }
}
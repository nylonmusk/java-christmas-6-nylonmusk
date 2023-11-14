package christmas.menuitem;

import christmas.domain.MenuItemRepository;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class MenuItemRepositoryTest {

    private MenuItemRepository menuItemRepository;

    @BeforeEach
    void setUp() {
        menuItemRepository = new MenuItemRepository();
    }

    private static Stream<Arguments> menuItems() {
        return Stream.of(
                Arguments.of("양송이수프", 6000),
                Arguments.of("타파스", 5500),
                Arguments.of("시저샐러드", 8000),
                Arguments.of("제로콜라", 3000),
                Arguments.of("초코케이크", 15000),
                Arguments.of("아이스크림", 5000),
                Arguments.of("티본스테이크", 55000),
                Arguments.of("해산물파스타", 35000)
        );
    }

    @ParameterizedTest
    @MethodSource("menuItems")
    void 존재하는_메뉴_아이템_가격_테스트(String itemName, int expectedPrice) {
        int price = menuItemRepository.findPriceByName(itemName);
        assertThat(price).isEqualTo(expectedPrice);
    }

    @Test
    void 존재하지_않는_메뉴_아이템_가격_테스트() {
        String nonExistingItemName = "존재하지 않는 메뉴 아이템 이름";
        int expectedPrice = 0;

        int price = menuItemRepository.findPriceByName(nonExistingItemName);
        assertThat(price).isEqualTo(expectedPrice);
    }
}

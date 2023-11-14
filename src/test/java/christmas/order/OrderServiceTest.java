package christmas.order;


import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Application;
import christmas.domain.OrderedItems;
import christmas.service.OrderService;
import christmas.validator.OrderValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceTest extends NsTest {

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(new OrderValidator());
    }

    @Test
    void 주문_파싱_및_항목_생성_테스트() {
        String orderWithComma = "티본스테이크-2, 크리스마스파스타-1";
        OrderedItems orderedItems = orderService.getOrderedItems(orderWithComma);

        assertThat(orderedItems.getItemsMap()).hasSize(2);
        assertThat(orderedItems.getItemsMap()).containsKeys("티본스테이크", "크리스마스파스타");
        assertThat(orderedItems.getItemsMap().get("티본스테이크")).isEqualTo(2);
        assertThat(orderedItems.getItemsMap().get("크리스마스파스타")).isEqualTo(1);
    }

    @Test
    void 주문_목록_포맷팅_테스트() {
        OrderedItems orderedItems = new OrderedItems();
        orderedItems.addItem("티본스테이크", 2);
        orderedItems.addItem("크리스마스파스타", 1);

        String formattedOrderList = orderService.getOrderList(orderedItems);

        String expectedFormat = """
                티본스테이크 2개
                크리스마스파스타 1개
                """;
        assertThat(formattedOrderList).isEqualTo(expectedFormat);
    }

    @Test
    void 유효한_주문_테스트() {
        String validOrder = "크리스마스파스타-1,티본스테이크-2,제로콜라-1";

        OrderedItems orderedItems = orderService.getOrderedItems(validOrder);

        assertThat(orderedItems.getItemsMap()).containsEntry("크리스마스파스타", 1);
        assertThat(orderedItems.getItemsMap()).containsEntry("티본스테이크", 2);
        assertThat(orderedItems.getItemsMap()).containsEntry("제로콜라", 1);
    }

    @Test
    void 잘못된_형식의_주문_테스트() {
        assertSimpleTest(() -> {
            runException("3", "크리스마스파스타-1-티본스테이크-2");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문 형식입니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}

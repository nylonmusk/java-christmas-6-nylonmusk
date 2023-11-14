package christmas.controller;

import christmas.domain.MenuItemRepository;
import christmas.service.OrderService;
import christmas.service.DiscountService;
import christmas.domain.ComplimentaryManager;
import christmas.domain.BadgeManager;
import christmas.domain.OrderCalculator;
import christmas.validator.OrderValidator;
import christmas.view.OutputView;
import christmas.view.InputView;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ControllerBuilderTest {

    private ControllerBuilder builder;

    @BeforeEach
    void setUp() {
        builder = new ControllerBuilder()
                .withOutputView(new OutputView())
                .withInputView(new InputView())
                .withOrderService(new OrderService(new OrderValidator()))
                .withDiscountService(new DiscountService())
                .withComplimentaryManager(new ComplimentaryManager())
                .withBadgeManager(new BadgeManager())
                .withOrderCalculator(new OrderCalculator(new MenuItemRepository()));
    }

    @Test
    void 모든_필드_할당_테스트() {
        Controller controller = builder.build();
        assertNotNull(controller);
    }

    @Test
    void 필드_누락_시_예외_발생_테스트() {
        ControllerBuilder incompleteBuilder = new ControllerBuilder()
                .withOutputView(new OutputView());
        assertThrows(IllegalStateException.class, incompleteBuilder::build);
    }
}

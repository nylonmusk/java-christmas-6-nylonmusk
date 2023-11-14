package christmas;

import christmas.controller.Controller;
import christmas.controller.ControllerBuilder;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.service.OrderService;
import christmas.service.DiscountService;
import christmas.domain.BadgeManager;
import christmas.domain.ComplimentaryManager;
import christmas.domain.MenuItemRepository;
import christmas.domain.OrderCalculator;
import christmas.validator.OrderValidator;

public class Application {
    public static void main(String[] args) {
        Controller controller = new ControllerBuilder()
                .withOutputView(new OutputView())
                .withInputView(new InputView())
                .withOrderService(new OrderService(new OrderValidator()))
                .withDiscountService(new DiscountService())
                .withComplimentaryManager(new ComplimentaryManager())
                .withBadgeManager(new BadgeManager())
                .withOrderCalculator(new OrderCalculator(new MenuItemRepository()))
                .build();

        controller.run();
    }
}

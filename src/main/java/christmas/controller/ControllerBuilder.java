package christmas.controller;

import christmas.constant.message.ErrorMessage;
import christmas.view.OutputView;
import christmas.view.InputView;
import christmas.service.OrderService;
import christmas.service.DiscountService;
import christmas.domain.BadgeManager;
import christmas.domain.ComplimentaryManager;
import christmas.domain.OrderCalculator;

public class ControllerBuilder {
    private OutputView outputView;
    private InputView inputView;
    private OrderService orderService;
    private DiscountService discountService;
    private ComplimentaryManager complimentaryManager;
    private BadgeManager badgeManager;
    private OrderCalculator orderCalculator;

    public ControllerBuilder withOutputView(OutputView outputView) {
        this.outputView = outputView;
        return this;
    }

    public ControllerBuilder withInputView(InputView inputView) {
        this.inputView = inputView;
        return this;
    }

    public ControllerBuilder withOrderService(OrderService orderService) {
        this.orderService = orderService;
        return this;
    }

    public ControllerBuilder withDiscountService(DiscountService discountService) {
        this.discountService = discountService;
        return this;
    }

    public ControllerBuilder withComplimentaryManager(ComplimentaryManager complimentaryManager) {
        this.complimentaryManager = complimentaryManager;
        return this;
    }

    public ControllerBuilder withBadgeManager(BadgeManager badgeManager) {
        this.badgeManager = badgeManager;
        return this;
    }

    public ControllerBuilder withOrderCalculator(OrderCalculator orderCalculator) {
        this.orderCalculator = orderCalculator;
        return this;
    }


    public OutputView getOutputView() {
        return outputView;
    }

    public InputView getInputView() {
        return inputView;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public DiscountService getDiscountService() {
        return discountService;
    }

    public ComplimentaryManager getComplimentaryManager() {
        return complimentaryManager;
    }

    public BadgeManager getBadgeManager() {
        return badgeManager;
    }

    public OrderCalculator getOrderCalculator() {
        return orderCalculator;
    }

    public Controller build() {
        validateFields();
        return new Controller(this);
    }

    private void validateFields() {
        if (outputView == null || inputView == null || orderService == null
                || discountService == null || complimentaryManager == null
                || badgeManager == null || orderCalculator == null) {
            throw new IllegalStateException(ErrorMessage.MISSING_OBJECT.getMessage());
        }
    }
}

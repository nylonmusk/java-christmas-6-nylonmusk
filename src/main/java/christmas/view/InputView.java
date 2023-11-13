package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.validator.DayValidator;
import christmas.validator.OrderValidator;

public class InputView {
    OutputView outputView = new OutputView();
    DayValidator dayValidator = new DayValidator();
    OrderValidator orderValidator = new OrderValidator();

    public int readDay() {
        try {
            outputView.askForRestaurantVisitDate();
            String input = getInput();
            dayValidator.validate(input);
            return Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return readDay();
        }
    }

    public String readOrderMenu() {
        try {
            outputView.requestMenuOrder();
            String orderWithComma = getInput();
            orderValidator.validateOrderFormat(orderWithComma);
            return orderWithComma;
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return readOrderMenu();
        }
    }

    public String getInput() {
        return Console.readLine();
    }
}

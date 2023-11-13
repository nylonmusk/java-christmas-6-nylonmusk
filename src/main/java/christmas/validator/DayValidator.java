package christmas.validator;

import christmas.constant.calendar.DayRange;
import christmas.constant.message.ErrorMessage;
import christmas.constant.regex.NumberRegex;

public final class DayValidator {
    public void validate(String dayWithString) {
        if (!isInputNumber(dayWithString) || isDayValid(dayWithString)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
    }

    private boolean isDayValid(String dayWithString) {
        int day = Integer.parseInt(dayWithString);
        return (DayRange.MIN_DAY.getValue() > day || day > DayRange.MAX_DAY.getValue());
    }

    private boolean isInputNumber(String input) {
        return input.matches(NumberRegex.DIGITS.getPattern());
    }

}

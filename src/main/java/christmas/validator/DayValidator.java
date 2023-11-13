package christmas.validator;

public final class DayValidator {
    public void validate(String dayWithString) {
        if (!isInputNumber(dayWithString) || isDayValid(dayWithString)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private boolean isDayValid(String dayWithString) {
        int day = Integer.parseInt(dayWithString);
        return (1 > day || day > 31);
    }

    private boolean isInputNumber(String input) {
        return input.matches("\\d+");
    }

}

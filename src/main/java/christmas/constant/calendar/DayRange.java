package christmas.constant.calendar;

public enum DayRange {
    MIN_DAY(1),
    MAX_DAY(31);

    private final int value;

    DayRange(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}


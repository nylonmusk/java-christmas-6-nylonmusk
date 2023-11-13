package christmas.constant.discount;

public enum DiscountValue {
    START_DISCOUNT(1000),
    DAILY_INCREMENT(100),
    WEEKDAY_DISCOUNT_PER_ITEM(2023),
    WEEKEND_DISCOUNT_PER_ITEM(2023),
    SPECIAL_DISCOUNT(1000),
    NO_DISCOUNT(0),

    MINIMUM_DISCOUNT_THRESHOLD(10000);

    private final int value;

    DiscountValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

package christmas.constant.order;

public enum OrderLimit {
    MAX_ITEMS(20),
    MIN_QUANTITY(1);

    private final int value;

    OrderLimit(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
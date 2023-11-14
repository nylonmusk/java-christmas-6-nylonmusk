package christmas.constant.order;

public enum OrderFormat {
    EXPECTED_PARTS_COUNT(2);

    private final int value;

    OrderFormat(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
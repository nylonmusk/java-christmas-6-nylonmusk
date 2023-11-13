package christmas.constant.complimentary;

public enum ComplimentaryDescription {
    COMPLIMENTARY_DISCOUNT("증정 이벤트: -");

    private final String description;

    ComplimentaryDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

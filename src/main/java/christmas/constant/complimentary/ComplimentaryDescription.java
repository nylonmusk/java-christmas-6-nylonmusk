package christmas.constant.complimentary;

public enum ComplimentaryDescription {
    COMPLIMENTARY_DISCOUNT("증정 이벤트: -"),
    NO_COMPLIMENTARY("없음\n");


    private final String description;

    ComplimentaryDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

package christmas.constant.discount;

public enum DiscountDescription {
    CHRISTMAS_D_DAY_DISCOUNT("크리스마스 디데이 할인: -"),
    WEEKDAY_DISCOUNT("평일 할인: -"),
    WEEKEND_DISCOUNT("주말 할인: -"),
    SPECIAL_DISCOUNT("특별 할인: -"),
    NO_DISCOUNT("없음\n"),
    EMPTY("");

    private final String description;

    DiscountDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}


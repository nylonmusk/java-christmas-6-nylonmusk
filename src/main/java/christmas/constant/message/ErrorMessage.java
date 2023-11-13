package christmas.constant.message;

public enum ErrorMessage {
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_ORDER_FORMAT("유효하지 않은 주문 형식입니다. 다시 입력해 주세요."),
    DUPLICATE_MENU_ITEM("중복된 메뉴 항목이 있습니다. 다시 입력해 주세요."),
    BEVERAGE_ONLY_ORDER("음료만 주문 시, 주문할 수 없습니다. 다시 입력해 주세요."),
    MAXIMUM_ITEMS_EXCEEDED("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요."),

    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ErrorPrefix.ERROR_PREFIX.getPrefix()+ message;
    }
}

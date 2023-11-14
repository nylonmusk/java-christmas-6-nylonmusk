package christmas.constant.message;

public enum OutputViewMessage {
    WELCOME("안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다."),
    ASK_VISIT_DATE("%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    REQUEST_MENU_ORDER("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    EVENT_BENEFITS_PREVIEW("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU("<주문 메뉴>"),
    TOTAL_AMOUNT_BEFORE_DISCOUNT("<할인 전 총주문 금액>"),
    COMPLIMENTARY_ITEMS("<증정 메뉴>"),
    DETAIL_BENEFITS("<혜택 내역>"),
    TOTAL_DISCOUNTS("<총혜택 금액>"),
    FINAL_ORDER_AMOUNT("<할인 후 예상 결제 금액>"),
    EVENT_BADGE("<%d월 이벤트 배지>"),
    EVENT_NOTICE("<이벤트 주의 사항>"),
    EVENT_NOTICE_DETAIL("총주문 금액 %s 이상부터 이벤트가 적용됩니다.");

    private final String message;

    OutputViewMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String formatWithMonth(int month) {
        return String.format(message, month);
    }

    public String formatWithDay(int day) {
        return String.format(message, day);
    }

    public String formatWithMonthAndDay(int month, int day) {
        return String.format(message, month, day);
    }

    public String formatWithMoney(String money) {
        return String.format(message, money);
    }
}

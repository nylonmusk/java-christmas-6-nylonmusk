package christmas.view;

import christmas.constant.discount.DiscountValue;
import christmas.util.FormatCurrencyUtil;

public class OutputView {
    public OutputView() {
    }

    public void printWelcomeMessage() {
        println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void askForRestaurantVisitDate() {
        println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }

    public void requestMenuOrder() {
        println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    }

    public void printPreviewOfEventBenefits() {
        println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        newLine();
    }

    public void printOrder(String formattedOrderList) {
        println("<주문 메뉴>");
        println(formattedOrderList);
    }

    public void printTotalOrderAmountBeforeDiscount(int totalPriceBeforeDiscount) {
        println("<할인 전 총주문 금액>");
        println(FormatCurrencyUtil.formatCurrency(totalPriceBeforeDiscount));
    }

    public void printComplimentaryItems(String complimentaryItems) {
        println("<증정 메뉴>");
        println(complimentaryItems);
    }

    public void printDetailBenefits(String discountInformation){
        println("<혜택 내역>");
        println(discountInformation);
    }

    public void printTotalDiscounts(int totalDiscounts) {
        println("<총혜택 금액>");
        if (totalDiscounts > DiscountValue.NO_DISCOUNT.getValue()) print("-");
        println(FormatCurrencyUtil.formatCurrency(totalDiscounts));
    }

    public void printFinalOrderAmount (int finalPrice){
        println("<할인 후 예상 결제 금액>");
        println(FormatCurrencyUtil.formatCurrency(finalPrice));
    }

    public void printBadge (String badge){
        println("<12월 이벤트 배지>");
        println(badge);
    }

    public void printEventNotice (int totalPriceBeforeDiscount){
        if (totalPriceBeforeDiscount < DiscountValue.MINIMUM_DISCOUNT_THRESHOLD.getValue()) {
            newLine();
            println("<이벤트 주의 사항>");
            println("총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.");
        }

    }

    private void println(String message) {
        System.out.println(message);
    }

    private void newLine() {
        System.out.println();
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    private void print(String message) {
        System.out.print(message);
    }
}

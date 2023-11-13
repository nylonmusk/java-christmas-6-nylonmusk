package christmas.view;

import christmas.constant.calendar.CurrentYearMonth;
import christmas.constant.discount.DiscountValue;
import christmas.constant.message.OutputViewMessage;
import christmas.util.CurrencyFormatter;

public class OutputView {
    public OutputView() {
    }

    public void printWelcomeMessage() {
        println(OutputViewMessage.WELCOME.formatWithMonth(CurrentYearMonth.MONTH.getDate()));
    }

    public void askForRestaurantVisitDate() {
        println(OutputViewMessage.ASK_VISIT_DATE.formatWithMonth(CurrentYearMonth.MONTH.getDate()));
    }

    public void requestMenuOrder() {
        println(OutputViewMessage.REQUEST_MENU_ORDER.getMessage());
    }

    public void printPreviewOfEventBenefits(int day) {
        println(OutputViewMessage.EVENT_BENEFITS_PREVIEW.formatWithMonthAndDay(CurrentYearMonth.MONTH.getDate(), day));
        newLine();
    }

    public void printOrder(String formattedOrderList) {
        println(OutputViewMessage.ORDER_MENU.getMessage());
        println(formattedOrderList);
    }

    public void printTotalOrderAmountBeforeDiscount(int totalPriceBeforeDiscount) {
        println(OutputViewMessage.TOTAL_AMOUNT_BEFORE_DISCOUNT.getMessage());
        println(CurrencyFormatter.formatCurrencyWithNewLine(totalPriceBeforeDiscount));
    }

    public void printComplimentaryItems(String complimentaryItems) {
        println(OutputViewMessage.COMPLIMENTARY_ITEMS.getMessage());
        println(complimentaryItems);
    }

    public void printDetailBenefits(String discountInformation){
        println(OutputViewMessage.DETAIL_BENEFITS.getMessage());
        println(discountInformation);
    }

    public void printTotalDiscounts(int totalDiscounts) {
        println(OutputViewMessage.TOTAL_DISCOUNTS.getMessage());
        if (totalDiscounts > DiscountValue.NO_DISCOUNT.getValue()) print("-");
        println(CurrencyFormatter.formatCurrencyWithNewLine(totalDiscounts));
    }

    public void printFinalOrderAmount (int finalPrice){
        println(OutputViewMessage.FINAL_ORDER_AMOUNT.getMessage());
        println(CurrencyFormatter.formatCurrencyWithNewLine(finalPrice));
    }

    public void printBadge (String badge){
        println(OutputViewMessage.EVENT_BADGE.formatWithMonth(CurrentYearMonth.MONTH.getDate()));
        println(badge);
    }

    public void printEventNotice (int totalPriceBeforeDiscount){
        if (totalPriceBeforeDiscount < DiscountValue.MINIMUM_DISCOUNT_QUALIFYING_AMOUNT.getValue()) {
            newLine();
            println(OutputViewMessage.EVENT_NOTICE.getMessage());
            println(OutputViewMessage.EVENT_NOTICE_DETAIL.formatWithMoney(CurrencyFormatter.formatCurrency(DiscountValue.MINIMUM_DISCOUNT_QUALIFYING_AMOUNT.getValue())));
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

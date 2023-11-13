package christmas.domain;

import christmas.constant.complimentary.ComplimentaryItem;

public class ComplimentaryManager {

    public String getComplimentary(int totalPriceBeforeDiscount) {
        StringBuilder complimentaryItems = new StringBuilder();

        for (ComplimentaryItem item : ComplimentaryItem.values()) {
            if (totalPriceBeforeDiscount >= item.getQualifyingAmount()) {
                complimentaryItems.append(item.getName())
                        .append(" ")
                        .append(item.getQuantity())
                        .append("개\n");
            }
        }

        if (complimentaryItems.isEmpty()) {
            complimentaryItems.append("없음\n");
        }
        return complimentaryItems.toString();
    }

}
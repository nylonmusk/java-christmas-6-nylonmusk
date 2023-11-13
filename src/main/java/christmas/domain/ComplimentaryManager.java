package christmas.domain;

import christmas.constant.complimentary.ComplimentaryDescription;
import christmas.constant.complimentary.ComplimentaryItem;
import christmas.util.ComplimentaryItemFormatter;

public class ComplimentaryManager {

    public String getComplimentary(int totalPriceBeforeDiscount) {
        StringBuilder complimentaryItems = new StringBuilder();

        for (ComplimentaryItem item : ComplimentaryItem.values()) {
            if (totalPriceBeforeDiscount >= item.getQualifyingAmount()) {
                complimentaryItems.append(ComplimentaryItemFormatter.format(item));
            }
        }

        if (complimentaryItems.isEmpty()) {
            complimentaryItems.append(ComplimentaryDescription.NO_COMPLIMENTARY.getDescription());
        }
        return complimentaryItems.toString();
    }

}
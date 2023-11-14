package christmas.domain;

import christmas.constant.complimentary.ComplimentaryDescription;
import christmas.constant.complimentary.ComplimentaryItem;
import christmas.util.ComplimentaryItemFormatter;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ComplimentaryManager {

    public String getComplimentary(int totalPriceBeforeDiscount) {
        String complimentaryItems = getComplimentaryItems(totalPriceBeforeDiscount);

        if (isComplimentaryAvailable(complimentaryItems)) {
            return complimentaryItems;
        }
        return ComplimentaryDescription.NO_COMPLIMENTARY.getDescription();
    }

    private String getComplimentaryItems(int totalPriceBeforeDiscount) {
        return Arrays.stream(ComplimentaryItem.values())
                .filter(item -> totalPriceBeforeDiscount >= item.getQualifyingAmount())
                .map(ComplimentaryItemFormatter::format)
                .collect(Collectors.joining());
    }

    private boolean isComplimentaryAvailable(String complimentaryItems) {
        return !complimentaryItems.isEmpty();
    }

}
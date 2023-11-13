package christmas.domain;

import christmas.constant.Badge;

public class BadgeManager {
    public String getBadge(int totalDiscounts) {
        String badge = Badge.getBadgeByAmount(totalDiscounts);
        if (!badge.isEmpty()) {
            return badge;
        }
        return "";
    }
}

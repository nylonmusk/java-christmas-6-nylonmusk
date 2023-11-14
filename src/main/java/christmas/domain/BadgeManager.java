package christmas.domain;

import christmas.constant.badge.Badge;

public class BadgeManager {

    public String getBadge(int totalDiscounts) {
        return Badge.getBadgeByAmount(totalDiscounts);
    }

}

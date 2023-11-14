package christmas.constant.badge;

public enum Badge {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000),
    NONE("없음", 0),
    NOTHING("", 0);

    private final String name;
    private final int qualifyingAmount;

    Badge(String name, int qualifyingAmount) {
        this.name = name;
        this.qualifyingAmount = qualifyingAmount;
    }

    public int getQualifyingAmount() {
        return this.qualifyingAmount;
    }

    public String getName() {
        return name;
    }

    public static String getBadgeByAmount(int totalDiscounts) {
        for (Badge badge : values()) {
            if (totalDiscounts >= badge.getQualifyingAmount()) {
                return badge.name;
            }
        }
        return NONE.name;
    }
}

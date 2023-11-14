package christmas.constant.complimentary;

public enum ComplimentaryItem {
    CHAMPAGNE("샴페인", 120000, 1);

    private final String name;
    private final int qualifyingAmount;
    private final int quantity;

    ComplimentaryItem(String name, int qualifyingAmount, int quantity) {
        this.name = name;
        this.qualifyingAmount = qualifyingAmount;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQualifyingAmount() {
        return qualifyingAmount;
    }

    public int getQuantity() {
        return quantity;
    }
}

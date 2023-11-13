package christmas.constant.menu;

public enum Beverage implements MenuItem {
    DIET_COLA("제로콜라", 3000),
    RED_WINE("레드와인", 60000),
    CHAMPAGNE("샴페인", 25000);

    private final String item;
    private final int price;

    Beverage(String item, int price) {
        this.item = item;
        this.price = price;
    }

    @Override
    public String getName() {
        return this.item;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    public static int getPrice(String name) {
        for (Beverage beverage : Beverage.values()) {
            if (name.equals(beverage.item)) {
                return beverage.price;
            }
        }
        return 0;
    }
}
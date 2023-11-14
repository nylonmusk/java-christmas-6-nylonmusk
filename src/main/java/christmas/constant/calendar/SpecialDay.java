package christmas.constant.calendar;

public enum SpecialDay {
    THIRD(3),
    TENTH(10),
    SEVENTEENTH(17),
    CHRISTMAS_EVE(24),
    CHRISTMAS(25),
    END_OF_MONTH(31);

    private final int day;

    SpecialDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public boolean isTodaySpecial(int day) {
        for (SpecialDay specialDay : SpecialDay.values()) {
            if (specialDay.day == day) {
                return true;
            }
        }
        return false;
    }
}

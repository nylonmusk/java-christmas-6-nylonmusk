package christmas.constant.calendar;

public enum CurrentYearMonth {
    YEAR(2023),
    MONTH(12);

    private final int date;

    CurrentYearMonth(int date) {
        this.date = date;
    }

    public int getDate() {
        return date;
    }
}

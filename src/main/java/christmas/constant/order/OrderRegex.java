package christmas.constant.order;

public enum OrderRegex {
    COMMA_SPACE(",\\s*"),
    HYPHEN("-");

    private final String pattern;

    OrderRegex(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}

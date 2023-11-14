package christmas.constant.regex;

public enum NumberRegex {
    DIGITS("\\d+");

    private final String pattern;

    NumberRegex(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}

package christmas.constant.message;

public enum ErrorPrefix {
    ERROR_PREFIX("[ERROR] ");

    private final String prefix;

    ErrorPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}

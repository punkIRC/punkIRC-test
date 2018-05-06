package de.rubenmaurer.punk.core.util;

public class Ask {

    private String message;

    private int lineCount;

    public String message() {
        return message;
    }

    public int lineCount() {
        return lineCount;
    }

    private Ask(String message, int lineCount) {
        this.message = message;
        this.lineCount = lineCount;
    }

    public static Ask create(String message, int lineCount) {
        return new Ask(message, lineCount);
    }
}

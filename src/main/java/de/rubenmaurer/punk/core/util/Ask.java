package de.rubenmaurer.punk.core.util;

public class Ask {

    private String message;

    private int lineCount;

    private int maxLineCount;

    public String message() {
        return message;
    }

    public int lineCount() {
        return lineCount;
    }

    public int maxLineCount() {
        return maxLineCount;
    }

    private Ask(String message, int lineCount) {
        this.message = message;
        this.lineCount = lineCount;
    }

    private Ask(String message, int lineCount, int maxLineCount) {
        this.message = message;
        this.lineCount = lineCount;
        this.maxLineCount = maxLineCount;
    }

    public static Ask create(String message, int lineCount) {
        return new Ask(message, lineCount);
    }

    public static Ask create(String message, int lineCount, int maxLineCount) {
        return new Ask(message, lineCount, maxLineCount);
    }
}

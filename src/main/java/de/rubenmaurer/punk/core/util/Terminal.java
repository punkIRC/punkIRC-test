package de.rubenmaurer.punk.core.util;

import org.apache.commons.lang3.StringUtils;

public class Terminal {

    private static final int LINE_LENGTH = 80;

    private static final int CAGE_SIZE = 28;

    public static String getDivider() {
        return getDivider("=");
    }

    public static String getDivider(String lineChar) {
        return String.format("%s\r\n", StringUtils.repeat(lineChar, LINE_LENGTH));
    }

    public static String center(String input) {
        return center(input, LINE_LENGTH);
    }

    public static String center(String input, int length) {
        return center(input, length, true);
    }

    public static String center(String input, int length, boolean newLine) {
        String format = "%s";
        if (newLine) format = "%s\r\n";

        return String.format(format, StringUtils.center(input, length));
    }

    public static String left(String input) {
        return StringUtils.left(input.intern(), LINE_LENGTH);
    }

    public static String right(String input) {
        return StringUtils.right(input.intern(), LINE_LENGTH);
    }

    public static String twoSidedColumn(String col1, String col2) {
        int space = LINE_LENGTH - (col1.length() + col2.length()) + 10;

        return String.format("%s%s%s", col1, StringUtils.repeat(" ", space), col2);
    }

    public static String cageStatus(String status) {
        return String.format("[%s]", center(status, CAGE_SIZE, false));
    }

    private static String upperCaseWordsOnly(String input) {
        StringBuilder sb = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (Character.isUpperCase(c)) sb.append(c);
        }

        return sb.toString();
    }
}

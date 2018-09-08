package de.rubenmaurer.punk.util;

import de.rubenmaurer.punk.Settings;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.fusesource.jansi.Ansi.ansi;

public class Terminal {

    private static final int LINE_LENGTH = 80;

    private static final int CAGE_SIZE = 28;

    public static void printHeader() {
        System.out.println(ansi()
                .render(Terminal.getDivider())
                .render(Terminal.center(String.format("Project Pricefield | [Version]: %s | [Build]: %s", Settings.version(), Settings.build())))
                .render(Terminal.getDivider())
                .render(Terminal.center(String.format("ID: %s", Settings.generateID())))
                .render(Terminal.getDivider()));
    }

    private static String getDivider() {
        return getDivider("=");
    }

    public static String getDivider(String lineChar) {
        return String.format("%s\r\n", StringUtils.repeat(lineChar, LINE_LENGTH));
    }

    public static String center(String input) {
        return center(input, LINE_LENGTH);
    }

    private static String center(String input, int length) {
        return center(input, length, true);
    }

    private static String center(String input, int length, boolean newLine) {
        String format = "%s";
        if (newLine) format = "%s\r\n";

        return String.format(format, StringUtils.center(input, length));
    }

    public static String twoSidedColumn(String col1, String col2) {
        return twoSidedColumn(col1, col2, 10, false);
    }

    private static String twoSidedColumn(String col1, String col2, int comp, boolean newLine) {
        int space = LINE_LENGTH - (col1.length() + col2.length()) + comp;

        if (newLine) {
            return String.format("%s%s%s\r\n", col1, StringUtils.repeat(" ", space), col2);
        }

        return String.format("%s%s%s", col1, StringUtils.repeat(" ", space), col2);
    }

    public static String cageStatus(String status) {
        return String.format("[%s]", center(status, CAGE_SIZE, false));
    }

    public static void printError(String message) {
        printError(message, "pricefield");
    }

    public static void printError(String message, String source) {
        if (message != null && !message.isEmpty()) {
            System.err.print(String.format("%s\r\n\r\n",
                    Template.get("LOG_ERROR")
                            .single("timestamp", new SimpleDateFormat("hh:mm:ss").format(new Date()))
                            .single("message", message)
                            .single("place", source).render()));
        }
    }

    /* === LOGGING === */

    public static void debugRecv(String message) {
        System.err.println(Template.recv(message));
    }

    public static void debugSend(String message) {
        System.err.println(Template.send(message));
    }

    public static void debugErro(String message) {
        System.err.println(Template.erro(message));
    }
}

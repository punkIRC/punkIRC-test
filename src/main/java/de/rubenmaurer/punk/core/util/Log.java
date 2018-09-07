package de.rubenmaurer.punk.core.util;

import de.rubenmaurer.punk.messages.Template;

public class Log {
    public static void debug(String message) {
        if (Settings.debug()) {
            System.err.println(template(message));
        }
    }

    public static void debug(String[] message) {
        if (Settings.debug()) {
            System.err.println(template(message));
        }
    }

    private static String template(String message) {
        return Template.get("DEBUG").single("message", String.format("\r\n%s", message)).render();
    }

    private static String template(String[] message) {
        return Template.get("DEBUG").single("message", message).render();
    }
}

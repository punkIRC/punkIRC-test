package de.rubenmaurer.punk.core.akka;

/**
 * Represents a question to gather data from a {@link ConnectionHandler}.
 * Mainly used for telling the {@link ConnectionHandler} what to send to the irc server,
 * or to gather the last received lines/ log entries.
 *
 * @author Ruben Maurer
 * @version 1.0
 * @since 1.0
 */
public class Ask {

    /**
     * The message to ask.
     */
    private String message;

    /**
     * Expected lines of the answer.
     */
    private int lineCount;

    /**
     * Getter for the message.
     *
     * @return the message
     */
    public String message() {
        return message;
    }

    /**
     * Getter for the count of lines.
     *
     * @return the count of lines.
     */
    int lineCount() {
        return lineCount;
    }

    /**
     * Constructor for constructing a new {@link Ask} object.
     *
     * @param message the message to ask.
     * @param lineCount the expected lines.
     */
    private Ask(String message, int lineCount) {
        this.message = message;
        this.lineCount = lineCount;
    }

    /**
     * Method for creating a new {@link Ask} object.
     *
     * @param message the message to ask.
     * @param lineCount the expected lines.
     * @return the new {@link Ask} object.
     */
    public static Ask create(String message, int lineCount) {
        return new Ask(message, lineCount);
    }
}

package de.rubenmaurer.punk.evaluation.antlr;

import de.rubenmaurer.punk.IRCLexer;
import de.rubenmaurer.punk.IRCParser;
import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.evaluation.Response;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.HashMap;
import java.util.Map;

/**
 * Parser for incoming messages from the irc server.
 *
 * @author Ruben Maurer
 * @version 1.0
 * @since 1.0
 */
public class Parser {

    /**
     * Parse a single message.
     *
     * @param sender the ${@link Client} which the message send
     * @param receiver the ${@link Client} which the message received
     * @param code the expected ${@link Response}
     * @param message the message to parse
     * @param values collection of values needed for the evaluation
     */
    public static void parse(Client sender, Client receiver, Response code, String message, Map<String, String> values) {
        if (!message.equals("")) {
            CharStream stream = CharStreams.fromString(message);

            IRCLexer lexer = new IRCLexer(stream);
            lexer.removeErrorListeners();
            lexer.addErrorListener(PricefieldErrorListener.INSTANCE);

            CommonTokenStream tokens = new CommonTokenStream(lexer);

            IRCParser parser = new IRCParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(PricefieldErrorListener.INSTANCE);

            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(new PricefieldGrammarListener(sender, receiver, code, values), parser.response());
        }
    }

    /**
     * Parse a single message.
     *
     * @param sender the ${@link Client} which the message send
     * @param response the expected ${@link Response}
     * @param message the message to parse
     */
    public static void parse(Client sender, Response response, String message) {
        parse(sender, sender, response, message, new HashMap<>());
    }

    /**
     * Parse a single message.
     *
     * @param sender the ${@link Client} which the message send
     * @param target the ${@link Client} which the message targeted
     * @param response the expected ${@link Response}
     * @param message the message to parse
     */
    public static void parse(Client sender, Client target, Response response, String message) {
        parse(sender, target, response, message, new HashMap<>());
    }

    /**
     * Parse a single message.
     *
     * @param sender the ${@link Client} which the message send
     * @param response the expected ${@link Response}
     * @param message the message to parse
     * @param values collection of values needed for the evaluation
     */
    public static void parse(Client sender, Response response, String message, HashMap<String, String> values) {
        parse(sender, sender, response, message, values);
    }
}

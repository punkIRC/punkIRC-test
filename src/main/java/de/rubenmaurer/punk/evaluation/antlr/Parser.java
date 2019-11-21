package de.rubenmaurer.punk.evaluation.antlr;

import de.rubenmaurer.punk.IRCLexer;
import de.rubenmaurer.punk.IRCParser;
import de.rubenmaurer.punk.Settings;
import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.evaluation.Response;
import de.rubenmaurer.punk.util.Template;
import de.rubenmaurer.punk.util.Terminal;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
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

    private static int ruleID;

    public static void useRule(int rule) {
        ruleID = rule;
    }

    private static ParseTree tree(IRCParser parser) {
        if (ruleID == IRCParser.RULE_quit) return parser.quit();
        if (ruleID == IRCParser.RULE_private_message) return parser.private_message();
        if (ruleID == IRCParser.RULE_notice) return parser.notice();
        if (ruleID == IRCParser.RULE_pong) return parser.pong();
        if (ruleID == IRCParser.RULE_server_response) return parser.server_response();
        if (ruleID == IRCParser.RULE_part) return parser.part();
        if (ruleID == IRCParser.RULE_topic) return parser.topic();

        return parser.response();
    }

    public static void reset() {
        ruleID = -1;
    }

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
        if (message.isEmpty()) throw new RuntimeException(Template.get("EMPTY_STRING_FOR_PARSER").render());
        if (Settings.devMode()) Terminal.parserLog(message);

        CharStream stream = CharStreams.fromString(message);

        IRCLexer lexer = new IRCLexer(stream);
        lexer.removeErrorListeners();
        lexer.addErrorListener(PricefieldErrorListener.INSTANCE);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        IRCParser parser = new IRCParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(PricefieldErrorListener.INSTANCE);

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new PricefieldGrammarListener(sender, receiver, code, values), tree(parser));

        reset();
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

package de.rubenmaurer.punk.evaluation.antlr;

import de.rubenmaurer.punk.IRCLexer;
import de.rubenmaurer.punk.IRCParser;
import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.evaluation.Response;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Parser {

    /**
     * Parse a message.
     *
     * @param message the message to parse
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

    public static void parse(Client sender, Response response, String message) {
        parse(sender, sender, response, message, new HashMap<>());
    }

    public static void parse(Client sender, Client target, Response response, String message) {
        parse(sender, target, response, message, new HashMap<>());
    }

    public static void parse(Client sender, Response response, String message, HashMap<String, String> values) {
        parse(sender, sender, response, message, values);
    }

    public static void parse(Client sender, Response response, LinkedList<String> messages, HashMap<String, String> values) {
        messages.forEach(s -> parse(sender, response, s, values));
    }
}

package de.rubenmaurer.punk.evaluation;

import de.rubenmaurer.punk.GrammarLexer;
import de.rubenmaurer.punk.GrammarListener;
import de.rubenmaurer.punk.GrammarParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Parser {

    /**
     * Parse a message.
     *
     * @param message the message to parse
     */
    public static void process(String message) {
        CharStream stream = CharStreams.fromString(message);

        GrammarLexer lexer = new GrammarLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);

        GrammarParser.ResponseContext context = parser.response();

        ParseTreeWalker walker = new ParseTreeWalker();
        GrammarListener listener = new PricefieldGrammarListener();

        walker.walk(listener, context);
    }
}

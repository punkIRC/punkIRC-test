package de.rubenmaurer.punk.evaluation.antlr;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;

/**
 * Listener for exceptions which occurs during parsing.
 *
 * @author Ruben Maurer
 * @version 1.0
 * @since 1.0
 */
public class PricefieldErrorListener extends BaseErrorListener {

    /**
     * Instance of ${@link PricefieldErrorListener}.
     */
    static final PricefieldErrorListener INSTANCE = new PricefieldErrorListener();

    /**
     * Handles a {@link ParseCancellationException}.
     *
     * @param recognizer the recognizer
     * @param offendingSymbol the offending symbol
     * @param line the line number
     * @param charPositionInLine the character position
     * @param msg the message
     * @param e the exception
     * @throws ParseCancellationException the exception
     */
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) throws ParseCancellationException {
        e.printStackTrace();

        throw new ParseCancellationException(String.format("String '%s' is not correct!", e));

        //throw new ParseCancellationException("line " + line + ":" + charPositionInLine + " " + msg);
    }
}

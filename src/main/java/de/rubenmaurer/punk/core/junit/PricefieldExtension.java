package de.rubenmaurer.punk.core.junit;

import de.rubenmaurer.punk.util.Terminal;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

/**
 * Extension for advanced exception handling.
 *
 * @author Ruben Maurer
 * @version 1.0
 * @since 1.0
 */
public class PricefieldExtension implements TestExecutionExceptionHandler {

    /**
     * Handles the exception which is thrown when a test fails.
     *
     * @param context the test context
     * @param throwable the exception
     * @throws Throwable throws an exception
     */
    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        Terminal.printError(throwable.getMessage(), context.getDisplayName());

        throw throwable;
    }
}

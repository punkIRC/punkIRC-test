package de.rubenmaurer.punk.core.junit;

import de.rubenmaurer.punk.util.Terminal;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

public class PricefieldExtension implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        Terminal.printError(throwable.getMessage(), context.getDisplayName());

        throw throwable;
    }
}

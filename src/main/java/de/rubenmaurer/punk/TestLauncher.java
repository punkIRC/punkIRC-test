package de.rubenmaurer.punk;

import de.rubenmaurer.punk.core.junit.PricefieldUnitListener;
import de.rubenmaurer.punk.util.Terminal;
import org.junit.platform.engine.DiscoverySelector;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Launcher for the irc tests.
 *
 * @author Ruben Maurer
 * @version 1.0
 * @since 1.0
 */
class TestLauncher {

    /**
     * Launches the irc tests.
     */
    static void launch() {
        List<DiscoverySelector> selectors = new LinkedList<>();

        Settings.tests().forEach(t -> selectors.add(DiscoverySelectors.selectClass(t)));
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request().selectors(selectors).build();

        org.junit.platform.launcher.Launcher launcher = LauncherFactory.create();

        // Register a listener of your choice
        TestExecutionListener listener = new PricefieldUnitListener();
        launcher.registerTestExecutionListeners(listener);

        launcher.execute(request);

        try {
            System.setOut(new PrintStream(new File(String.format("%s/%s/results.log", Settings.logs(), Pricefield.runtimeID))));

            PricefieldUnitListener.testResults.forEach((status, test) -> {
                System.out.println(String.format("%s: ", status.name()));
                test.forEach( t -> System.out.println(String.format("- %s", t)));

                System.out.println();
            });
        } catch (FileNotFoundException e) {
            Terminal.debugErro(e.getMessage());
        }

        System.exit(0);
    }
}

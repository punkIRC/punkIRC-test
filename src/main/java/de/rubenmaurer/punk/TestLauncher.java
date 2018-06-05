package de.rubenmaurer.punk;

import de.rubenmaurer.punk.test.connection.BasicConnection;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

public class TestLauncher {
    public static void launch() {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(selectClass(BasicConnection.class)).build();

        org.junit.platform.launcher.Launcher launcher = LauncherFactory.create();

        // Register a listener of your choice
        TestExecutionListener listener = new SummaryGeneratingListener();
        launcher.registerTestExecutionListeners(listener);

        launcher.execute(request);
        System.out.println(((SummaryGeneratingListener) listener).getSummary().getTestsFoundCount());
    }
}

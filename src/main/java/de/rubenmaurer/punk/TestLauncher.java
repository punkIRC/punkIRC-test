package de.rubenmaurer.punk;

import de.rubenmaurer.punk.core.junit.PricefieldUnitListener;
import de.rubenmaurer.punk.core.util.Settings;
import org.junit.platform.engine.DiscoverySelector;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;

import java.util.LinkedList;
import java.util.List;

class TestLauncher {
    static void launch() {
        List<DiscoverySelector> selectors = new LinkedList<>();

        Settings.tests().forEach(t -> selectors.add(DiscoverySelectors.selectClass(t)));
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request().selectors(selectors).build();

        org.junit.platform.launcher.Launcher launcher = LauncherFactory.create();

        // Register a listener of your choice
        TestExecutionListener listener = new PricefieldUnitListener();
        launcher.registerTestExecutionListeners(listener);

        launcher.execute(request);
    }
}

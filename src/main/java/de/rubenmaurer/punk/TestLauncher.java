package de.rubenmaurer.punk;

import de.rubenmaurer.punk.core.junit.PricefieldUnitListener;
import de.rubenmaurer.punk.test.connection.BasicConnection;
import de.rubenmaurer.punk.test.ping.Ping;
import de.rubenmaurer.punk.test.ping.Pong;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

class TestLauncher {
    static void launch() {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(
                        //selectClass(BasicConnection.class),
                        selectClass(Ping.class),
                        selectClass(Pong.class)).build();

        org.junit.platform.launcher.Launcher launcher = LauncherFactory.create();

        // Register a listener of your choice
        TestExecutionListener listener = new PricefieldUnitListener();
        launcher.registerTestExecutionListeners(listener);

        launcher.execute(request);
    }
}
